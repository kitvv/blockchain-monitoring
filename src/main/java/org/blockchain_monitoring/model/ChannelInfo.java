package org.blockchain_monitoring.model;

/**
 * Created by Ruslan Kryukov on 16/05/2017.
 */
public class ChannelInfo {
    private String name;
    private Long blockCount;

    public ChannelInfo(String name, Long blockCount) {
        this.name = name;
        this.blockCount = blockCount;
    }

    public String getName() {
        return name;
    }

    public Long getBlockCount() {
        return blockCount;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Height: " + String.valueOf(blockCount);
    }
}
