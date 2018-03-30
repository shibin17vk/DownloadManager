package com.testapp.problem1.feature_downloadfile.data_layer;

import com.testapp.problem1.feature_downloadfile.callbacks.ItemPercentCallback;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author shibin
 * @version 1.0
 * @date 30/03/18
 */

public class ItemDownloadPercentObserver {

    private ObservableEmitter percentageObservableEmitter;
    private Disposable downloadPercentDisposable;
    private final ItemPercentCallback callback;

    public ItemDownloadPercentObserver(ItemPercentCallback callback) {
        this.callback=callback;
        ObservableOnSubscribe observableOnSubscribe = new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                percentageObservableEmitter = e;
            }
        };

        final Observable observable = Observable.create(observableOnSubscribe);

        final Observer subscriber = getObserver();
        observable.subscribeWith(subscriber);
    }

    public ObservableEmitter getPercentageObservableEmitter() {
        return percentageObservableEmitter;
    }

    private Observer getObserver() {
        return new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                downloadPercentDisposable = d;
            }

            @Override
            public void onNext(Object value) {
                if (!(value instanceof DownloadItem)) {
                    return;
                }
                callback.updateDownloadableItem((DownloadItem) value);
            }

            @Override
            public void onError(Throwable e) {
                if (downloadPercentDisposable != null) {
                    downloadPercentDisposable.dispose();
                }
            }

            @Override
            public void onComplete() {
                if (downloadPercentDisposable != null) {
                    downloadPercentDisposable.dispose();
                }
            }
        };
    }

    public void performCleanUp() {
        if (downloadPercentDisposable != null) {
            downloadPercentDisposable.dispose();
        }
    }
}
