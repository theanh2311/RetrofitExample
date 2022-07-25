package com.example.demo4.select;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelectProd {
    @GET("API_Select.php")
    Call<ServerResponseSelectProd> getProduct();
}
