package com.bigbang.baseframe.activity;

import com.bigbang.baseframe.R;
import com.bigbang.baseframe.common.BaseActivity;
import com.bigbang.baseframe.presenter.SplashPresenter;
import com.bigbang.baseframe.utils.ToastUtils;
import com.bigbang.baseframe.view.SplashView;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter(this, this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.delayToMainActivity();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void jumpToMain() {
        jumpToActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onError(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }

    @Override
    protected void onDestroy() {
        getWindow().setBackgroundDrawable(null);//theme里面设置了windowBackground，需要清理
        super.onDestroy();
    }
}
