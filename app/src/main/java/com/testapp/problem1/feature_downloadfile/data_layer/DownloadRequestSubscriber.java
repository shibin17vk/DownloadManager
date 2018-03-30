package com.testapp.problem1.feature_downloadfile.data_layer;

import com.testapp.problem1.feature_downloadfile.callbacks.ItemDownloadCallback;
import com.testapp.problem1.utils.AppConstants;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * @author shibin
 * @version 1.0
 * @date 30/03/18
 */

public class DownloadRequestSubscriber {

    private Subscription downloadRequestsSubscription;
    private FlowableEmitter downloadsFlowableEmitter;
    private ItemDownloadCallback itemDownloadCallback;

    public DownloadRequestSubscriber(ItemDownloadCallback itemDownloadCallback) {
        this.itemDownloadCallback = itemDownloadCallback;
        FlowableOnSubscribe flowableOnSubscribe = new FlowableOnSubscribe() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {
                downloadsFlowableEmitter = e;
            }
        };

        final Flowable flowable = Flowable.create(flowableOnSubscribe, BackpressureStrategy.BUFFER);
        final Subscriber subscriber = getSubscriber();
        flowable.subscribeWith(subscriber);
    }

    public void requestNextItem(int number) {
        downloadRequestsSubscription.request(number);
    }

    public void emitNextItem(DownloadItem downloadableItem) {
        downloadsFlowableEmitter.onNext(downloadableItem);
    }

    private Subscriber getSubscriber() {

        return new Subscriber() {
            @Override
            public void onSubscribe(Subscription s) {
                downloadRequestsSubscription = s;
                downloadRequestsSubscription.request(AppConstants.DOWNLOAD_POOL_LIMIT);
            }

            @Override
            public void onNext(Object o) {
                if (!(o instanceof DownloadItem)) {
                    return;
                }
                itemDownloadCallback.onDownloadStarted((DownloadItem)o);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void performCleanUp() {
        if (downloadRequestsSubscription != null) {
            downloadRequestsSubscription.cancel();
        }
    }
}
