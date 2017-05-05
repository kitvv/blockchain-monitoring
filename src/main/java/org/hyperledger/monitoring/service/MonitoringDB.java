package org.hyperledger.monitoring.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.hyperledger.fabric.protos.peer.Query;
import org.hyperledger.monitoring.api.InfluxDestroyer;
import org.hyperledger.monitoring.api.InfluxDestroyerException;
import org.hyperledger.monitoring.api.InfluxSearcher;
import org.hyperledger.monitoring.api.InfluxWriter;
import org.hyperledger.monitoring.model.PeerInfo;
import org.hyperledger.monitoring.model.PeerStatus;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitoringDB {

    private static final Logger log = LoggerFactory.getLogger(MonitoringDB.class);

    @Autowired
    private InfluxWriter influxWriter;

    @Autowired
    private InfluxSearcher influxSearcher;

    @Autowired
    private InfluxDestroyer influxDestroyer;

    private static final String FIELD_CHAINCODE = "chaincode";
    private static final String FIELD_CHANNEL = "channel";
    private static final String FIELD_STATUS = "status";

    // PAY ATTENTION !!!
    // contract TAG name != FIELD name
    private static final String TAG_PEER = "TAG_PEER";
    private static final String TAG_STATUS = "TAG_STATUS";

    public void writePeerInfo(PeerInfo peerInfo) {
        try {

            log.info("MonitoringDB.writePeerInfo");
            if (peerInfo.getName() == null || peerInfo.getName().isEmpty() || peerInfo.getStatus() == null) {
                throw new IllegalArgumentException("peerId and peerStatus can't be null");
            }
            log.info("peerId = [" + peerInfo.getName() + "], chaincodInfoList = [" + peerInfo.getChaincodInfoList() + "], channelList = [" + peerInfo.getChannelList() + "], peerStatus = [" + peerInfo.getStatus() + "]");

            final HashMap<String, String> tags = new HashMap<String, String>() {{
                put(TAG_PEER, peerInfo.getName());
                put(TAG_STATUS, peerInfo.getStatus().name());
            }};

            final Point.Builder builder = Point.measurement(peerInfo.getName())
                    .addField(FIELD_STATUS, peerInfo.getStatus().ordinal());

            tags.entrySet().forEach(tag -> builder.tag(tag.getKey(), tag.getValue()));

            if (peerInfo.getChaincodInfoList() != null) {
                final List<String> chancodeNameList = peerInfo.getChaincodInfoList().stream().map(Query.ChaincodeInfo::getName)
                        .collect(Collectors.toList());
                final String chaincodes = chancodeNameList.toString();
                builder.addField(FIELD_CHAINCODE, chaincodes.substring(1, chaincodes.length() - 1));
                final String channels = peerInfo.getChannelList().toString();
                builder.addField(FIELD_CHANNEL, channels.substring(1, channels.length() - 1));
            }

            final Point point = builder.build();
            updatePeerInfo(peerInfo.getName(), peerInfo.getStatus(), tags, point);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void updatePeerInfo(String peerId, PeerStatus peerStatus, HashMap<String, String> tags, Point point) {
        if (isNotExistsByPeerIdAndStatus(peerId, peerStatus.name())) {
            try {
                tags.replace(TAG_STATUS, peerStatus.name(), peerStatus.getOtherStatus().name());
                influxDestroyer.deleteMeasurementByTags(peerId, tags);
            } catch (InfluxDestroyerException e) {
                e.printStackTrace();
            }

            influxWriter.write(point);
        }
    }

    /**
     * Проверяем существуют ли значения в БД по peerId и status
     *
     * @param peerId - measurement
     * @param status - tag
     * @return true - exists, false - not found
     */
    private boolean isNotExistsByPeerIdAndStatus(final String peerId, final String status) {
        final QueryResult query = influxSearcher.query("SELECT status FROM \"" + peerId + "\" WHERE \"" + TAG_STATUS + "\" = '" + status + "'");
        final List<QueryResult.Result> results = query.getResults();

        return results.isEmpty() || (results.size() == 1 && results.get(0).getSeries() == null);
    }
}

