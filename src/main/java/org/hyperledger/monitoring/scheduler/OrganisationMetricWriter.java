package org.hyperledger.monitoring.scheduler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import org.hyperledger.fabric.client.fly.FlyClient;
import org.hyperledger.fabric.client.fly.spring.FlyNet;
import org.hyperledger.fabric.protos.peer.Query;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.monitoring.model.PeerInfo;
import org.hyperledger.monitoring.model.PeerStatus;
import org.hyperledger.monitoring.service.MonitoringDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            final Set<String> channelList = new HashSet<>();
            PeerStatus status;
            try {
                chaincodInfoList.addAll(flyClient.queryInstalledChaincodes(peer));
                channelList.addAll(flyClient.queryChannels(peer));
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
