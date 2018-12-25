package com.wyl.github.rxjavaretrofitokhttp.net.api;


import com.wyl.github.rxjavaretrofitokhttp.bean.ChaptersBean;
import com.wyl.github.rxjavaretrofitokhttp.bean.LoginData;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface RetrofitService {

    @GET(Api.chaptersUrl)
    Observable<ChaptersBean> getChapters();

    @POST(Api.registerUrl)
    @FormUrlEncoded
    Observable<LoginData> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
}
