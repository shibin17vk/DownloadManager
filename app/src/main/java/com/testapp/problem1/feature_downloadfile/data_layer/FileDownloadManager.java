package com.testapp.problem1.feature_downloadfile.data_layer;

import android.app.DownloadManager;
import android.content.Context;

/**
 * @author shibin
 * @version 1.0
 * @date 30/03/18
 */

public class FileDownloadManager{

    private DownloadManager mDownloadManager;

    private static FileDownloadManager mFileDownloadManager;

    private FileDownloadManager(Context context) {
        mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public static final FileDownloadManager getInstance(Context context) {
        if(mFileDownloadManager == null) {
            mFileDownloadManager = new FileDownloadManager(context);
        }
        return mFileDownloadManager;
    }

    public synchronized long downloadFile(DownloadItem downloadItem, ItemDownloadPercentObserver
            itemDownloadPercentObserver) {
        long downloadId = RxDownloadManagerHelper.enqueueDownload(mDownloadManager, downloadItem.getItemDownloadUrl());
        downloadItem.setDownloadId(downloadId);
        RxDownloadManagerHelper.queryDownloadPercents(mDownloadManager, downloadItem,itemDownloadPercentObserver.getPercentageObservableEmitter());
        return downloadId;
    }

}
