package com.bigbang.baseframe.utils;

import com.bigbang.baseframe.bean.response.NormalResponse;
import com.bigbang.baseframe.common.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HttpUtil {

    /**
     * Request Http ， 不用MVP的时候可以用这个请求网络
     */
    public static <T> void subscribeNetworkTask(Observable<T> observable, final BasePresenter.MyObserver<T> myObserver) {
        observable.subscribe(handlerObserver(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T t) {
                myObserver.onMyNext(t);
            }

            @Override
            public void onError(Throwable e) {
                myObserver.onMyError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        }));
    }

    /**
     * 1。处理NormalResponse在response正常返回200，但是code不为0（发生错误）的情况
     * 2。处理Exception
     */
    public static <T> Observer<T> handlerObserver(final Observer<T> observer) {
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                observer.onSubscribe(d);
            }

            @Override
            public void onNext(T t) {
                if (t instanceof NormalResponse) {
                    NormalResponse normalRsp = (NormalResponse) t;
                    int status = normalRsp.getCode();
                    if (status == 0) {
                        observer.onNext(t);
                    } else {
                        onError(new Throwable(normalRsp.getMsg()));
                    }
                } else {
                    observer.onNext(t);
                }
            }

            @Override
            public void onError(Throwable e) {
                String errorMessage = ExceptionUtil.getHttpExceptionMessage(e);
                observer.onError(new Throwable(errorMessage));
            }

            @Override
            public void onComplete() {
                observer.onComplete();
            }
        };
    }
}
