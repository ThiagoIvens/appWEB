package com.thiagoivens.chocolateria.models.order;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thiagoivens.chocolateria.R;
import com.thiagoivens.chocolateria.models.order_item.ObjWithOrderItem;
import com.thiagoivens.chocolateria.models.order_item.OrderItem;
import com.thiagoivens.chocolateria.models.order_item.OrderItemAPI;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class OrderFragment extends Fragment {
    public static final String API = "http://ivenstr.pythonanywhere.com/";

    View v;
    private RecyclerView myrecyclerview;
    OrderRecyclerAdapter recyclerAdapter;
    ObjWithOrderItem objWithOrderItem;

    public OrderFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_confirm_ordem_with_shipping_address, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.recycler_order_id);
        recyclerAdapter = new OrderRecyclerAdapter(getContext(), new ArrayList<>());
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // GET ALL PRODUCTS
        OrderItemAPI orderItemAPI = retrofit.create(OrderItemAPI.class);

        Call<ObjWithOrderItem> call = orderItemAPI.getOrderItems();
        call.enqueue(new Callback<ObjWithOrderItem>() {
            @Override
            public void onResponse(Response<ObjWithOrderItem> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    objWithOrderItem = response.body();
                    for (OrderItem oi : objWithOrderItem.getResults()) {
                        recyclerAdapter.insertOrderItem(oi);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("LOG product_fragment_OnFailure", t.getMessage());
            }
        });
    }
}
