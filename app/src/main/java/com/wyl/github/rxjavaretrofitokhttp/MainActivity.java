package com.wyl.github.rxjavaretrofitokhttp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wyl.github.rxjavaretrofitokhttp.bean.ChaptersBean;
import com.wyl.github.rxjavaretrofitokhttp.net.api.RetrofitService;
import com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper.HttpRequestHelper;
import com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper.MySubscriber;
import com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper.RetrofitRequest;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TextView getContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        getContent = (TextView) findViewById(R.id.get_content);
        findViewById(R.id.tv_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitRequest.getInstance().doHttp(new HttpRequestHelper(context) {
                    @Override
                    public Observable getObservable(RetrofitService retrofitService) {
                        return retrofitService.getChapters();
                    }

                    @Override
                    public Subscriber getSubscriber() {
                        MySubscriber<?> mySubscriber = new MySubscriber<ChaptersBean>(context) {
                            @Override
                            public void onNext(ChaptersBean data) {
                                getContent.setText(data.toString());
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                        };
                        return mySubscriber;
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
