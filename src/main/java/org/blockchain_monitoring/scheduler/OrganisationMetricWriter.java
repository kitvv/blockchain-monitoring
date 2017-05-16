package org.blockchain_monitoring.scheduler;

import org.blockchain_monitoring.fly_client.FlyClient;
import org.blockchain_monitoring.fly_client_spring.FlyNet;
import org.blockchain_monitoring.model.ChannelInfo;
import org.blockchain_monitoring.model.PeerInfo;
import org.blockchain_monitoring.model.PeerStatus;
import org.blockchain_monitoring.service.MonitoringDB;
import org.hyperledger.fabric.protos.common.Ledger;
import org.hyperledger.fabric.protos.peer.Query;
import org.hyperledger.fabric.sdk.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Component
public class OrganisationMetricWriter implements Consumer<FlyNet.Organization> {

    private static final Logger log = LoggerFactory.getLogger(OrganisationMetricWriter.class);

    @Autowired
    public MonitoringDB monitoringDB;

    @Override
    public void accept(final FlyNet.Organization organization) {
        for (Peer peer : organization.getPeers()) {
            Optional<PeerInfo> peerInfo = getPeerInfo(peer, organization.getFlyClient());
            if (peerInfo.isPresent()) {
                monitoringDB.writePeerInfo(peerInfo.get());
            }
        }
    }

    private Optional<PeerInfo> getPeerInfo(final Peer peer, FlyClient flyClient) {
        try {
            final List<Query.ChaincodeInfo> chaincodInfoList = new ArrayList<>();
            final Set<ChannelInfo> channelList = new HashSet<>();

            PeerStatus status;
            try {
                chaincodInfoList.addAll(flyClient.queryInstalledChaincodes(peer));
                for(String name: flyClient.queryChannels(peer)) {
                    flyClient.findChannelClient(name).ifPresent(x -> {
                        try {
                            Ledger.BlockchainInfo info = x.getChain().queryBlockchainInfo(peer).getBlockchainInfo();
                            channelList.add(new ChannelInfo(name, info.getHeight()));
                        } catch (Exception ignore) {}
                    });
                }
                status = PeerStatus.UP;
            } catch (Exception e) {
                status = PeerStatus.DOWN;
            }

            return Optional.of(PeerInfo.of(peer.getName(), chaincodInfoList, channelList, status));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }
}
