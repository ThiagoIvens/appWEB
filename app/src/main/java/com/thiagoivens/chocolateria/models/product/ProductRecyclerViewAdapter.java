package com.thiagoivens.chocolateria.models.product;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thiagoivens.chocolateria.R;
import com.thiagoivens.chocolateria.models.order.OrderRecyclerAdapter;

import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder> {
    public static final String API = "http://ivenstr.pythonanywhere.com/";

    Context mContext;
    List<Product> productData;
    Dialog myDialog;
    OrderRecyclerAdapter orderRecyclerAdapter;

    public ProductRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        this.productData = null;
    }

    public ProductRecyclerViewAdapter(Context mContext, List<Product> productData) {
        this.mContext = mContext;
        this.productData = productData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);
        orderRecyclerAdapter = new OrderRecyclerAdapter(mContext);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.product_dialog);

        vHolder.item_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv = myDialog.findViewById(R.id.details_product_name_id);
                TextView dialog_digital_tv = myDialog.findViewById(R.id.details_product_digital_id);
                TextView dialog_price_tv = myDialog.findViewById(R.id.details_product_price_id);
                ImageView dialog_img = myDialog.findViewById(R.id.details_img_id);
                Button dialog_btn = myDialog.findViewById(R.id.product_btn_add_id);

                dialog_name_tv.setText( productData.get(vHolder.getAdapterPosition()).getName() );

                if(productData.get( vHolder.getAdapterPosition()).getDigital() )
                    dialog_digital_tv.setText( "Produto Digital" );
                else
                    dialog_digital_tv.setText( "Produto Fisico" );

                dialog_price_tv.setText( "R$ "+ productData.get(vHolder.getAdapterPosition() ).getPrice() );
//                dialog_img.setImageResource( productData.get(vHolder.getAdapterPosition() ).getImg());
                Picasso.get().load(  productData.get(vHolder.getAdapterPosition()).getImg() ).into( dialog_img );

                // ADD_ON_CART
                dialog_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productData.get(vHolder.getAdapterPosition());
//                        orderRecyclerAdapter.insertOrderItem(new OrderItem(
//                                1,
//                                productData.get(vHolder.getAdapterPosition()),
//                                null
//                        ));
                    }
                });

                myDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(productData.get(position).getName());
        Picasso.get().load(  productData.get(position).getImg() ).into( holder.img_product );
        holder.tv_price.setText("R$ " + String.valueOf(productData.get(position).getPrice()) );
    }

    @Override
    public int getItemCount() {
        return productData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_product;
        private TextView tv_name;
        private TextView tv_price;
        private ImageView img_product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_product = (LinearLayout) itemView.findViewById(R.id.item_product_id);
            tv_name = (TextView) itemView.findViewById(R.id.name_product);
            tv_price = (TextView) itemView.findViewById(R.id.price_product);
            img_product = (ImageView) itemView.findViewById(R.id.img_product);
        }
    }

    public void insertProduct(Product product){
        productData.add(product);
        notifyItemInserted(getItemCount());
    }
}