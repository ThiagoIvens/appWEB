package com.thiagoivens.chocolateria.models.order_item;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface OrderItemAPI {

    @GET("orderItem/")
    Call<ObjWithOrderItem> getOrderItems();

    // adiciona uma nova ordem de item ao banco
    // ainda precisa configurar
    @FormUrlEncoded
    @POST("orderItem/")
    Call<OrderItem> postNewOrderItem(@Field("quantity") String quantity, @Field("product") String productURL, @Field("order") String orderURL);
}
