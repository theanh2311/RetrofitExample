package com.example.demo4.insert;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsertProduct {
    @FormUrlEncoded
    @POST("API_Insert.php")
    Call<ServerResponseProduct> insertProduct(
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description
    );
}
