package com.wyl.github.rxjavaretrofitokhttp.net.retrofitwrapper;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Converter;


public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String body = value.string();
//        try {
//            JSONObject jsonObject = new JSONObject(body);
//            if(jsonObject.optInt("status") == 200){
//                JSONObject jsonObject1 = new JSONObject();
//                body = jsonObject1.toString();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        /**
         * 因为你只能对ResponseBody读取一次 ,
         * 如果你调用了response.body().string()两次或者response.body().charStream()两次就会出现这个异常,
         * 先调用string()再调用charStream()也不可以
         */
        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(Util.UTF_8) : Util.UTF_8;
        InputStream inputStream = new ByteArrayInputStream(body.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }

}
