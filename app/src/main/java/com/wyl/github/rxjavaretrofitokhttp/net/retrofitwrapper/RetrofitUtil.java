package com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper;

import android.text.TextUtils;

import com.wyl.github.rxjavaretrofitokhttp.net.HeaderUtils;
import com.wyl.github.rxjavaretrofitokhttp.net.api.Api;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RetrofitUtil {
    private static final int DEFAULT_TIMEOUT = 10;

    public static String getBaseUrl() {
        return Api.baseUrl;
    }

    /**
     * 修改http头文件
     *
     * @return
     */
    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        Map<?, ?> headerMap = HeaderUtils.generalHeaders();
                        if (headerMap != null) {
                            for (Map.Entry<?, ?> entry : headerMap.entrySet()) {
                                String key = String.valueOf(entry.getKey());
                                if (!TextUtils.isEmpty(key)) {
                                    builder.addHeader(key, String.valueOf(entry.getValue()));
                                }
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
