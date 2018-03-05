package com.bigbang.baseframe.common;

import com.bigbang.baseframe.utils.ExceptionUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/5.
 * ObserverFactory用来统一管理Observer生命周期，重新createObserver的时候会dispose前一个Observer，BaseActivity调用destroy的时候会dispose所有Observer
 * 如果不想统一管理特殊的Observer，可以直接new Observer()而不用ObserverFactory来创建
 */

public class ObserverFactory {

    private static HashMap<String, Disposable> mDisposableMap = new HashMap<>();

    private static void putObserver(String observerTag, Disposable disposable) {
        mDisposableMap.put(observerTag, disposable);
    }

    private static void removeObserver(String observerTag) {
        Disposable d = mDisposableMap.get(observerTag);
        if (d != null && !d.isDisposed()) {
            d.dispose();
            mDisposableMap.remove(observerTag);
        }
        mDisposableMap.remove(observerTag);
    }

    public static void removeAllObserver() {
        Iterator<Map.Entry<String, Disposable>> iterator = mDisposableMap.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().getValue().dispose();
        }
        mDisposableMap.clear();
    }

    public static <T> Observer<T> createObserver(final String observerTag, final MyObserver<T> observer) {

        removeObserver(observerTag);

        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                putObserver(observerTag, d);
            }

            @Override
            public void onNext(T t) {
                observer.onMyNext(t);
            }

            @Override
            public void onError(Throwable e) {
                String errorMessage = ExceptionUtil.getHttpExceptionMessage(e);
                observer.onMyError(errorMessage);
                removeObserver(observerTag);
            }

            @Override
            public void onComplete() {
                removeObserver(observerTag);
            }
        };
    }

    public interface MyObserver<T> {
        void onMyNext(T t);

        void onMyError(String errorMessage);
    }
}
