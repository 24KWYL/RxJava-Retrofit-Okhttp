package com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper;


import com.wyl.github.rxjavaretrofitokhttp.net.api.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RetrofitRequest {

    private Retrofit retrofit;
    private RetrofitService retrofitService;
    private static volatile RetrofitRequest INSTANCE;

    public RetrofitRequest(){
        retrofit = new Retrofit.Builder()
                .client(RetrofitUtil.genericClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(RetrofitUtil.getBaseUrl())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static RetrofitRequest getInstance(){
        if(INSTANCE == null){
            synchronized (RetrofitRequest.class){
                if(INSTANCE == null){
                    INSTANCE = new RetrofitRequest();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * post、get等请求
     * @param httpRequestHelper
     */
     public void doHttp(HttpRequestHelper httpRequestHelper) {
        httpRequestHelper.getObservable(retrofitService)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
           .subscribe(httpRequestHelper.getSubscriber());
    }



}
