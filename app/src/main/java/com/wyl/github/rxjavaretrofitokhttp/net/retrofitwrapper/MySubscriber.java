package com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;
import rx.Subscriber;



public abstract class MySubscriber<T> extends Subscriber<T>{
    private boolean isShowProgress = true;
    private WeakReference<Context> contextWeakReference;
    private Context context;

    public MySubscriber(Context context) {
        this.contextWeakReference = new WeakReference<>(context);
        init(false);
    }

    public MySubscriber(Context context, boolean isShowProgress) {
        this.isShowProgress = isShowProgress;
        this.contextWeakReference = new WeakReference<>(context);
        init(false);
    }

    public MySubscriber(Context context, boolean isShowProgress, boolean isCanCancel) {
        this.isShowProgress = isShowProgress;
        this.contextWeakReference = new WeakReference<>(context);
        init(isCanCancel);
    }

    private void init(boolean isCanCancel){
        context = contextWeakReference.get();
    }




    @Override
    public void onCompleted() {
        onFinish();
        doUnsubscribe();
    }


    @Override
    public void onError(Throwable e) {
        onFinish();
        doUnsubscribe();
    }

    public void onFinish(){}

    @Override
    public void onStart() {
        super.onStart();
    }

    //解绑
    public void doUnsubscribe(){
        if(!isUnsubscribed()){
            unsubscribe();
        }
    }
}
