package com.bigbang.baseframe.view;


import com.bigbang.baseframe.bean.response.SearchResponseBean;

/**
 * Created by Administrator on 2018/2/7.
 */

public interface MainView {
    void onSearchResponse(SearchResponseBean searchResponseBean);

    void onSearchError(String error);
}
