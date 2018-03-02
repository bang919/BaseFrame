package com.bigbang.baseframe.presenter;

import android.content.Context;

import com.bigbang.baseframe.bean.response.SearchResponseBean;
import com.bigbang.baseframe.common.BasePresenter;
import com.bigbang.baseframe.model.MainModel;
import com.bigbang.baseframe.utils.ExceptionUtil;
import com.bigbang.baseframe.view.MainView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Administrator on 2018/2/7.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mMainModel;
    private Disposable mMainDisposable;

    public MainPresenter(Context context, MainView view, LifecycleProvider<ActivityEvent> activityLifecycleProvider) {
        super(context, view, activityLifecycleProvider);
        mMainModel = new MainModel(activityLifecycleProvider);
    }

    public void search(String tag) {
        if(mMainDisposable!=null){
            mMainDisposable.dispose();
            mMainDisposable = null;
        }
        mMainModel.search(tag, new Observer<SearchResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                mMainDisposable = d;
            }

            @Override
            public void onNext(SearchResponseBean searchResponseBean) {
                mView.onSearchResponse(searchResponseBean);
            }

            @Override
            public void onError(Throwable e) {
                mView.onSearchError(ExceptionUtil.getHttpExceptionMessage(e));
                mMainDisposable = null;
            }

            @Override
            public void onComplete() {
                mMainDisposable = null;
            }
        });
    }
}
