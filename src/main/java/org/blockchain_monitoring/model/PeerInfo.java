package org.blockchain_monitoring.model;

import org.hyperledger.fabric.protos.peer.Query;

import java.util.List;
import java.util.Set;

public class PeerInfo {
    private final String name;
    private final List<Query.ChaincodeInfo> chaincodInfoList;
    private final Set<ChannelInfo> channelList;
    private final PeerStatus status;

    private PeerInfo(final String name,
                     final List<Query.ChaincodeInfo> chaincodInfoList,
                     final Set<ChannelInfo> channelList,
                     final PeerStatus status) {
        this.name = name;
        this.chaincodInfoList = chaincodInfoList;
        this.channelList = channelList;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public List<Query.ChaincodeInfo> getChaincodInfoList() {
        return chaincodInfoList;
    }

    public Set<ChannelInfo> getChannelList() {
        return channelList;
    }

    public PeerStatus getStatus() {
        return status;
    }

    public static PeerInfo of(String name, List<Query.ChaincodeInfo> chaincodInfoList, Set<ChannelInfo> channelList, PeerStatus status) {
        return new PeerInfo(name, chaincodInfoList, channelList, status);
    }
}
