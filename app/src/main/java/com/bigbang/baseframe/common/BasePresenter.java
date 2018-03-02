package com.bigbang.baseframe.common;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by Leo on 2017/5/18.
 * presenter 基类
 */

public class BasePresenter<V> {

    protected V mView;
    protected Context mContext;
    protected LifecycleProvider<ActivityEvent> mActivityLifecycleProvider;

    public BasePresenter(Context context, V view) {
        this(context, view, null);
    }

    public BasePresenter(Context context, V view, LifecycleProvider<ActivityEvent> activityLifecycleProvider) {
        mView = view;
        mContext = context;
        mActivityLifecycleProvider = activityLifecycleProvider;
    }


    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
