package com.thiagoivens.chocolateria.models.product;

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

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ProductFragment extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    ProductRecyclerViewAdapter recyclerAdapter;
    ObjWithProduct objetoEstranhoComProduto;

    public static final String API = "http://ivenstr.pythonanywhere.com/";

    public ProductFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_product, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.product_recycler_id);
        recyclerAdapter = new ProductRecyclerViewAdapter(getContext(), new ArrayList<Product>());
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API)
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build();


            // GET ALL PRODUCTS
            ProductAPI productAPI = retrofit.create(ProductAPI.class);

            Call<ObjWithProduct> call = productAPI.getProducts();
            call.enqueue(new Callback<ObjWithProduct>() {
                @Override
                public void onResponse(Response<ObjWithProduct> response, Retrofit retrofit) {
                    if (response.isSuccess()){
                        objetoEstranhoComProduto = response.body();
                        for(Product p : objetoEstranhoComProduto.getResults()){
                            recyclerAdapter.insertProduct(p);
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
