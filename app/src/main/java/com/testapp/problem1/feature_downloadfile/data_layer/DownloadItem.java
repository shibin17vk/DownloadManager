package com.testapp.problem1.feature_downloadfile.data_layer;

/**
 * @author shibin
 * @version 1.0
 * @date 30/03/18
 */

public class DownloadItem {

    private String id;
    private long downloadId;
    private String itemTitle;
    private int itemCoverId;
    private int downloadingStatus;
    private String itemDownloadUrl;
    private int itemDownloadPercent;
    private long lastEmittedDownloadPercent = -1;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(final long downloadId) {
        this.downloadId = downloadId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(final String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemCoverId() {
        return itemCoverId;
    }

    public void setItemCoverId(final int itemCoverId) {
        this.itemCoverId = itemCoverId;
    }

    public int getDownloadingStatus() {
        return downloadingStatus;
    }

    public void setDownloadingStatus(final int downloadingStatus) {
        this.downloadingStatus = downloadingStatus;
    }

    public String getItemDownloadUrl() {
        return itemDownloadUrl;
    }

    public void setItemDownloadUrl(final String itemDownloadUrl) {
        this.itemDownloadUrl = itemDownloadUrl;
    }

    public int getItemDownloadPercent() {
        return itemDownloadPercent;
    }

    public void setItemDownloadPercent(final int itemDownloadPercent) {
        this.itemDownloadPercent = itemDownloadPercent;
    }

    public long getLastEmittedDownloadPercent() {
        return lastEmittedDownloadPercent;
    }

    public void setLastEmittedDownloadPercent(final long lastEmittedDownloadPercent) {
        this.lastEmittedDownloadPercent = lastEmittedDownloadPercent;
    }
}
