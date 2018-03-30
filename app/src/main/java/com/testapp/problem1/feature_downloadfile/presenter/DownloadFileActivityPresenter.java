package com.testapp.problem1.feature_downloadfile.presenter;

import android.content.Context;

import com.testapp.problem1.feature_downloadfile.callbacks.DownloadFileActivityCallback;
import com.testapp.problem1.feature_downloadfile.callbacks.ItemDownloadCallback;
import com.testapp.problem1.feature_downloadfile.callbacks.ItemPercentCallback;
import com.testapp.problem1.feature_downloadfile.data_layer.DownloadItem;
import com.testapp.problem1.feature_downloadfile.data_layer.DownloadRequestSubscriber;
import com.testapp.problem1.feature_downloadfile.data_layer.FileDownloadManager;
import com.testapp.problem1.feature_downloadfile.data_layer.ItemDownloadPercentObserver;
import com.testapp.problem1.utils.AppConstants;

/**
 * @author shibin
 * @version 1.0
 * @date 30/03/18
 */

public class DownloadFileActivityPresenter extends BasePresenter<DownloadFileActivityCallback>
        implements ItemDownloadCallback, ItemPercentCallback {

    private ItemDownloadPercentObserver mItemDownloadPercentObserver;
    private DownloadRequestSubscriber mDownloadRequestsSubscriber;
    private DownloadFileActivityCallback mDownloadFileActivityCallback;

    public DownloadFileActivityPresenter() {
        mDownloadRequestsSubscriber = new DownloadRequestSubscriber(this);
        mItemDownloadPercentObserver = new ItemDownloadPercentObserver(this);
    }

    public void requestDownloadFile(Context context, String fileUrl) {
        DownloadItem downloadableItem = new DownloadItem();
        downloadableItem.setItemDownloadUrl(fileUrl);
        downloadableItem.setDownloadingStatus(AppConstants.DOWNLOAD_STATE_IN_PROGRESS);
        updateDownloadableItem(downloadableItem);
        FileDownloadManager fileDownloadManager = FileDownloadManager.getInstance(context);
        long downloadId = fileDownloadManager.downloadFile(downloadableItem,mItemDownloadPercentObserver);
        downloadableItem.setDownloadId(downloadId);
    }

    @Override
    public void registerCallback(final DownloadFileActivityCallback callback) {
        super.callBack = callback;
    }

    @Override
    public void unRegisterCallback() {
        super.callBack = null;
        mItemDownloadPercentObserver.performCleanUp();
        mDownloadRequestsSubscriber.performCleanUp();
    }

    @Override
    public void updateDownloadableItem(final DownloadItem downloadableItem) {
        if(downloadableItem.getDownloadingStatus() == AppConstants.DOWNLOAD_STATE_IN_PROGRESS) {
            callBack.onDownloadUpdate(downloadableItem.getItemDownloadPercent());
        } else if(downloadableItem.getDownloadingStatus() == AppConstants.DOWNLOAD_STATE_IDLE) {
            callBack.onDownloadPause();
        } else if(downloadableItem.getItemDownloadPercent() == 100) {
            callBack.onDownloadSuccess();
        }
    }

    @Override
    public void onDownloadEnqueued(final DownloadItem downloadItem) {
        callBack.onDownloadUpdate(downloadItem.getItemDownloadPercent());
    }

    @Override
    public void onDownloadStarted(final DownloadItem downloadItem) {
        callBack.onDownloadUpdate(downloadItem.getItemDownloadPercent());
    }

    @Override
    public void onDownloadComplete() {
        callBack.onDownloadSuccess();
    }
}
