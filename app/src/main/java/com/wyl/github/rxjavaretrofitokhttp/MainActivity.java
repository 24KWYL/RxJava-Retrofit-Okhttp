package com.wyl.github.rxjavaretrofitokhttp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wyl.github.rxjavaretrofitokhttp.bean.ChaptersBean;
import com.wyl.github.rxjavaretrofitokhttp.bean.LoginData;
import com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper.RetrofitRequest;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TextView getContent;
    private TextView postContent;
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        getContent = (TextView) findViewById(R.id.get_content);
        postContent = (TextView) findViewById(R.id.post_content);
        userName= (EditText) findViewById(R.id.user_name);
        password= (EditText) findViewById(R.id.password);
        findViewById(R.id.tv_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitRequest.getInstance().doHttps(RetrofitRequest.getInstance().retrofitService.getChapters(), new Subscriber<ChaptersBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ChaptersBean chaptersBean) {
                        getContent.setText(chaptersBean.toString());
                    }
                });
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitRequest.getInstance().doHttps(RetrofitRequest.getInstance().retrofitService.register(userName.getText().toString(), password.getText().toString(), password.getText().toString()), new Subscriber<LoginData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("wyl", e.toString());
                    }

                    @Override
                    public void onNext(LoginData o) {
                        postContent.setText(o.toString());
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
