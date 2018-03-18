package com.bigbang.baseframe.presenter;

import android.content.Context;

import com.bigbang.baseframe.bean.response.SearchResponseBean;
import com.bigbang.baseframe.common.BasePresenter;
import com.bigbang.baseframe.model.MainModel;
import com.bigbang.baseframe.view.MainView;


/**
 * Created by Administrator on 2018/2/7.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mMainModel;

    public MainPresenter(Context context, MainView view) {
        super(context, view);
        mMainModel = new MainModel();
    }

    public void search(String tag) {
        final String observerTag = getClass() + "search";
        subscribeNetworkTask(observerTag, mMainModel.search(tag), new MyObserver<SearchResponseBean>() {
            @Override
            public void onMyNext(SearchResponseBean searchResponseBean) {
                mView.onSearchResponse(searchResponseBean);
            }

            @Override
            public void onMyError(String errorMessage) {
                mView.onSearchError(errorMessage);
            }
        });
    }
}
