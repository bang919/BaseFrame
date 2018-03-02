package com.bigbang.baseframe.http;


import com.bigbang.baseframe.bean.response.SearchResponseBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/8/17.
 */

public interface ApiInterface {

    @Streaming
    @GET
        //下载文件
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

    @GET("v2/movie/search")
    Observable<SearchResponseBean> search(@Query("q") String query, @Query("tag") String tag, @Query("start") Integer start, @Query("count") Integer count);
}
