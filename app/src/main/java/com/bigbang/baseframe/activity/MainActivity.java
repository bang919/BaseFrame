package com.bigbang.baseframe.activity;

import com.bigbang.baseframe.R;
import com.bigbang.baseframe.bean.response.SearchResponseBean;
import com.bigbang.baseframe.common.BaseActivity;
import com.bigbang.baseframe.dialog.NormalDialog;
import com.bigbang.baseframe.presenter.MainPresenter;
import com.bigbang.baseframe.utils.ToastUtils;
import com.bigbang.baseframe.view.MainView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this, this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.search("喜剧");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onSearchResponse(SearchResponseBean searchResponseBean) {
        ToastUtils.showShort(searchResponseBean.toString());
        new NormalDialog(this, "Yest", "Not", "Descriptions", null, null).show();
    }

    @Override
    public void onSearchError(String error) {
        ToastUtils.showShort(error);
    }
}
