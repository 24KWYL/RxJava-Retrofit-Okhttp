package com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper;

import android.text.TextUtils;

import com.wyl.github.rxjavaretrofitokhttp.net.HeaderUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RetrofitUtil {
    private static final int DEFAULT_TIMEOUT = 10;
    public static int flagTestOrOnline = 0; // =0表示默认环境 =1表示测试 =2表示线上

    /**
     * 获取baseUrl 可以在测试的时候修改flagTestOrOnline 切换环境
     * 0 默认环境
     * 1 测试环境
     * 2 正式环境
     */
    public static String getBaseUrl(){
//        switch (flagTestOrOnline){
//            case 0:
//                return Api.BASE_URL;
//            case 1:
//                return Api.TEST_BASE_URL;
//            case 2:
//                return Api.REAL_BASE_URL;
//            default: return Api.BASE_URL;
//        }
        return "";
    }

    /**
     * 修改http头文件
     * @return
     */
    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        Map<?,?> headerMap = HeaderUtils.generalHeaders();
                        for(Map.Entry<?,?> entry : headerMap.entrySet()){
                            String key = String.valueOf(entry.getKey());
                            if(!TextUtils.isEmpty(key)){
                                builder.addHeader(key, String.valueOf(entry.getValue()));
                            }
                        }
                        Request request = builder.build();
                        Response response = chain.proceed(request);
                        return response;
                    }
                }).connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).retryOnConnectionFailure(false)
                .build();
        return httpClient;
    }


}
