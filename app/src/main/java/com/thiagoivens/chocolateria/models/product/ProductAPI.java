package com.thiagoivens.chocolateria.models.product;

import retrofit.Call;
import retrofit.http.GET;

public interface ProductAPI {

    @GET("product/")
    Call<ObjWithProduct> getProducts();

//    @GET("product/{id}")
//    static Call<ObjWithProduct> getOneProduct(@Path("{id}") int id);
}
