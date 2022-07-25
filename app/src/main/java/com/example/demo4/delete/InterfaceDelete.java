package com.example.demo4.delete;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDelete {
    @FormUrlEncoded
    @POST("API_Delete.php")
    Call<ServerResponseDelete> deletePrd(@Field("pid") String pid);
}
