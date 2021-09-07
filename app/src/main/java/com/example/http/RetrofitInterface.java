package com.example.http;


import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/s?wd=retrofit&rsv_spt=1&rsv_iqid=0x806382810008c760&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_dl=tb&rsv_enter=0&oq=%25E5%25AD%2597%25E8%258A%2582%25E8%25BD%25AC%25E6%258D%25A2&rsv_btype=t&inputT=6863&rsv_t=16a8FDcrZYRJePi6ZzXBdfnr%2FDRGLH0bXy0osTC3%2BNYk8KLGV3quk1WNmFD%2B93rJU7Or&rsv_pq=ef6a846700016eae&rsv_sug3=57&rsv_sug1=35&rsv_sug7=100&rsv_sug2=0&rsv_sug4=6863")
    Call<ResponseBody> response();
}
