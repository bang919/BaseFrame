package com.bigbang.baseframe.model;


import com.bigbang.baseframe.bean.response.SearchResponseBean;
import com.bigbang.baseframe.http.HttpClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/2/7.
 */

public class MainModel {

    public void search(String tag, Observer<? super SearchResponseBean> observer) {
        HttpClient.getApiInterface().search(null, tag, 0, 20).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
