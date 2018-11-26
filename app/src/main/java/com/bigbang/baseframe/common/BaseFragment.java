package com.bigbang.baseframe.common;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.Observable;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P mPresenter;
    private View mRootView;

    /**
     * 获取xml布局
     */
    protected abstract int getLayout();

    /**
     * 初始化presenter
     */
    protected abstract P initPresenter();

    /**
     * 初始化Fragment
     */
    protected abstract void initFragment();

    public <T extends View> T findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayout(), container, false);
        mPresenter = initPresenter();
        if (mPresenter == null) {
            mPresenter = (P) new BasePresenter<>(getContext(), null);
        }
        initFragment();
        return mRootView;
    }

    /**
     * 不使用MVP模式的时候网络请求可以使用以下方法
     */
    protected <T> void subscribeNetworkTask(Observable<T> observable) {
        mPresenter.subscribeNetworkTask(observable);
    }

    protected <T> void subscribeNetworkTask(Observable<T> observable, BasePresenter.MyObserver<T> myObserver) {
        mPresenter.subscribeNetworkTask(observable, myObserver);
    }

    protected <T> void subscribeNetworkTask(String observerTag, Observable<T> observable, BasePresenter.MyObserver<T> myObserver) {
        mPresenter.subscribeNetworkTask(observerTag, observable, myObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.destroy();
    }
}