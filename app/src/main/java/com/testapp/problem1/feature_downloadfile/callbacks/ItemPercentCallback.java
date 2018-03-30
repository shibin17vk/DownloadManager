package com.testapp.problem1.feature_downloadfile.callbacks;

import com.testapp.problem1.feature_downloadfile.data_layer.DownloadItem;

/**
 * @author shibin
 * @version 1.0
 * @date 30/03/18
 */

public interface ItemPercentCallback {
    void updateDownloadableItem(DownloadItem downloadableItem);
}
