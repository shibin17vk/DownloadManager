package com.testapp.problem1.feature_downloadfile.callbacks;

/**
 * @author shibin
 * @version 1.0
 * @date 30/03/18
 */

public interface DownloadFileActivityCallback {

    public void onDownloadUpdate(int downloadProgress);

    public void onDownloadSuccess();

    public void onDownloadFailure();

    public void onDownloadPause();

}
