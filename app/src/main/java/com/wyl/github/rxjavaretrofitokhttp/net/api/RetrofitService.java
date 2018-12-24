package com.wyl.github.rxjavaretrofitokhttp.net.api;



import com.wyl.github.rxjavaretrofitokhttp.bean.ChaptersBean;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitService {

    @GET(Api.chaptersUrl)
    Observable<ChaptersBean> getChapters();
}
