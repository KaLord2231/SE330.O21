package com.example.retrofit_demo.ApiService;

import com.example.retrofit_demo.model.Post;
import com.example.retrofit_demo.model.location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api {
  //https://ipinfo.io/161.185.160.93/geo
  //link api POST: https://jsonplaceholder.typicode.com/posts
    Gson gson= new GsonBuilder()
          .setDateFormat("yyyy-mm-dd HH:mm:ss")
          .create();
    api Api= new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
          .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(api.class);
    @GET("161.185.160.93/geo")
    Call<location> callAPI();
    @POST("posts")
    Call<Post> sendPost(@Body Post post);
}
