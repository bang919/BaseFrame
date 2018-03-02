package com.bigbang.baseframe.model;


import com.bigbang.baseframe.bean.response.SearchResponseBean;
import com.bigbang.baseframe.http.HttpClient;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/2/7.
 */

public class MainModel {

    private LifecycleProvider<ActivityEvent> activityLifecycleProvider;

    public MainModel(LifecycleProvider<ActivityEvent> activityLifecycleProvider) {
        this.activityLifecycleProvider = activityLifecycleProvider;
    }

    public void search(String tag, Observer<? super SearchResponseBean> observer) {
        HttpClient.getApiInterface().search(null, tag, 0, 20).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activityLifecycleProvider.<SearchResponseBean>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(observer);
    }
}
