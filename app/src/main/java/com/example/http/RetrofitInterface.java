package com.example.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @GET("")
    Call<List<Integer>> response(@Path("") String s);
}
