package com.thiagoivens.chocolateria.models.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thiagoivens.chocolateria.R;
import com.thiagoivens.chocolateria.models.order_item.OrderItem;

import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.MyViewHolder> {
    public static final String API = "http://ivenstr.pythonanywhere.com/";

    Context mContext;
    List<OrderItem> orderItemData;

    public OrderRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        this.orderItemData = null;
    }

    public OrderRecyclerAdapter(Context mContext, List<OrderItem> orderItemData) {
        this.mContext = mContext;
        this.orderItemData = orderItemData;
    }


    @NonNull
    @Override
    public OrderRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_order_item_product, parent, false);
        final OrderRecyclerAdapter.MyViewHolder vHolder = new OrderRecyclerAdapter.MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerAdapter.MyViewHolder holder, int position) {
        Picasso.get().load(  orderItemData.get(position).getProduct().getImg()  ).into( holder.img_product );

        holder.tv_name.setText( orderItemData.get(position).getProduct().getName() );
        holder.tv_price.setText( "R$ " + String.valueOf(orderItemData.get(position).getProduct().getPrice()) );
        holder.tv_quantity.setText( orderItemData.get(position).getQuantity() );
    }

    @Override
    public int getItemCount() {
        return orderItemData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_product;
        private TextView tv_name;
        private TextView tv_price;
        private TextView tv_quantity;
        private ImageView img_product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_product = (LinearLayout) itemView.findViewById(R.id.item_product_id);
            tv_name = (TextView) itemView.findViewById(R.id.name_product);
            tv_price = (TextView) itemView.findViewById(R.id.price_product);
            tv_quantity = (TextView) itemView.findViewById(R.id.quantity_id);
            img_product = (ImageView) itemView.findViewById(R.id.img_product);
        }
    }

    public void insertOrderItem(OrderItem orderItem){
        orderItemData.add(orderItem);
        notifyItemInserted(getItemCount());
    }
}
