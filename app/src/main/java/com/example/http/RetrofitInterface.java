package com.example.http;


import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("api/data/Android/10/1")
    Call<ResponseBody> response();
}
