package com.bigbang.baseframe.presenter;

import android.content.Context;

import com.bigbang.baseframe.common.BasePresenter;
import com.bigbang.baseframe.view.SplashView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class SplashPresenter extends BasePresenter<SplashView> {

    private static final int DELAY_TIME = 3000;//3s delay to MainActivity

    public SplashPresenter(Context context, SplashView view) {
        super(context, view);
    }

    public void delayToMainActivity() {
        subscribeNetworkTask(getClass().getSimpleName().concat("delayToMainActivity"), Observable.interval(DELAY_TIME, TimeUnit.MILLISECONDS).take(1), new MyObserver<Long>() {
            @Override
            public void onMyNext(Long aLong) {
                mView.jumpToMain();
            }

            @Override
            public void onMyError(String errorMessage) {
                mView.onError(errorMessage);
            }
        });

    }
}
