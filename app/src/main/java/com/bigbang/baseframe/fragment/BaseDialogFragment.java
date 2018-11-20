package com.bigbang.baseframe.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.bigbang.baseframe.R;
import com.bigbang.baseframe.common.BasePresenter;


public abstract class BaseDialogFragment<P extends BasePresenter> extends DialogFragment {

    protected P mPresenter;
    //是否已启动过。。用来修复一个bug：DialogFragment打开Activity，再返回DialogFragment会出现Fragment启动动画（-1.第一次进入，0.resume进入只设置animate_dialog_exit，1.多次resume）
    private int hadMeet = -1;
    private View mLoadingView;
    private View mRootView;

    public BaseDialogFragment() {
        setStyle(0, R.style.FullScreenLightDialog);
    }

    /**
     * 获取xml布局
     */
    protected abstract int getLayout();

    /**
     * 初始化presenter
     */
    protected abstract P initPresenter();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    public void setLoadingVisibility(boolean isVisibility) {
        mLoadingView.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
    }

    public boolean isLoadingVisibility() {
        if (mLoadingView.getVisibility() == View.VISIBLE) {
            return true;
        }
        return false;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    @Override
    public void onResume() {
        super.onResume();
        //修复一个bug：DialogFragment打开Activity，再返回DialogFragment会出现Fragment启动动画
        if (hadMeet > 0) {
            return;
        } else if (hadMeet == 0) {//已启动过
            getDialog().getWindow().setWindowAnimations(R.style.animate_dialog_exit);
        }
        hadMeet++;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable());
        getDialog().getWindow().setWindowAnimations(R.style.animate_dialog_enter_and_exit);

        mRootView = inflater.inflate(getLayout(), container, false);
        addLoadingView(mRootView);
        onViewPagerFragmentCreate();
        return mRootView;
    }

    private void addLoadingView(View rootView) {
        mLoadingView = LayoutInflater.from(rootView.getContext()).inflate(R.layout.loading_layout, (ViewGroup) rootView, false);
        ((ViewGroup) rootView).addView(mLoadingView);
    }

    protected void onViewPagerFragmentCreate() {
        mPresenter = initPresenter();
        initView();
        initEvent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.destroy();
    }
}
