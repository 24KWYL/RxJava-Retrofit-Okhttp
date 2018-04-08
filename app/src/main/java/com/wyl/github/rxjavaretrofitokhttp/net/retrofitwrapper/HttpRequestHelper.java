package com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.wyl.github.rxjavaretrofitokhttp.net.api.RetrofitService;

import rx.Observable;
import rx.Subscriber;


public abstract class HttpRequestHelper {

    public HttpRequestHelper(Context context){
//        if(context != null){ //将每一个subscriber放入基础activity中  统一解绑
//            ((AppCompatActivity)context).addSubscriber(getSubscriber());
//        }
    }

    //获取RetrofitService中的具体Observable
    public abstract Observable getObservable(RetrofitService retrofitService);

    //获取观察者(得到请求数据后的操作)
    public abstract Subscriber getSubscriber();
}
