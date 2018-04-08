package com.wyl.github.rxjavaretrofitokhttp.net;

import android.text.TextUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class RequestParams implements Serializable {

    public final HashMap<String, String> params = new HashMap<String, String>();
    private HashMap<String, JSONObject> jsonObjectParams = new HashMap<String, JSONObject>();
    private HashMap<String,JSONArray> jsonArrayParams = new HashMap<>();

    public RequestParams(){
        init();
    }

    public RequestParams(final String key , String value){
        init();
        put(key,value);
    }


    public void clearParam(){
        params.clear();
        jsonObjectParams.clear();
        jsonArrayParams.clear();
    }

    private void init(){
        params.clear();
        jsonObjectParams.clear();
        jsonArrayParams.clear();
    }

    public void put(String key, Object value) {
        if (!TextUtils.isEmpty(key) && value != null) {
            params.put(key, String.valueOf(value));
        }
    }


    public void putAll(RequestParams par){
        if(par!=null && par.params!=null){
            this.params.putAll(par.params);
        }
    }

    public void put(String key, JSONObject value) {
        if (!TextUtils.isEmpty(key) && value != null){
            jsonObjectParams.put(key,value);
        }
    }

    public void put(String key, JSONArray value) {
        if (!TextUtils.isEmpty(key) && value != null){
            jsonArrayParams.put(key,value);
        }
    }

    //获取所有数据 以json形式给出
    public RequestBody getParamsByJson(){
        RequestBody body = null;
        JSONObject jsonObject = null;
        if(params.size() > 0){
            jsonObject = new JSONObject(params);
        }
        if(jsonObjectParams.size() > 0){
            if(jsonObject == null){
                jsonObject = new JSONObject(jsonObjectParams);
            }else{
                addMapToJsonObject(jsonObject,jsonObjectParams);
            }
        }
        if(jsonArrayParams.size() > 0){
            if(jsonObject == null){
                jsonObject = new JSONObject(jsonArrayParams);
            }else{
                addMapToJsonObject(jsonObject,jsonArrayParams);
            }
        }
        if(jsonObject != null){
            JSONObject newJsonObject = new JSONObject();
            try {
                newJsonObject.put("data",jsonObject);
            }catch (JSONException e){
                e.printStackTrace();
            }
            body =  RequestBody.create(MediaType.parse("application/json; charset=utf-8"),newJsonObject.toString());
        }
        return body;
    }

    //将map加入jsonObject中
    private void addMapToJsonObject(JSONObject jsonObject, Map<?,?> map){
        for(Map.Entry<?,?> entry : map.entrySet()){
            String key = (String) entry.getKey();
            if (!TextUtils.isEmpty(key)) {
                try {
                    jsonObject.put(key,entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
