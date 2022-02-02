package com.nextsuntech.kdf1.Cart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.kdf1.Cart.AddToCartActivity;
import com.nextsuntech.kdf1.Dashboard.DashboardActivity;
import com.nextsuntech.kdf1.Model.GetCartDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.R;

import java.util.List;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {

    Context mContext;
    List<GetCartDataModel> getCartDataModels;

    public AddToCartAdapter(Context mContext, List<GetCartDataModel> getCartDataModels) {
        this.mContext = mContext;
        this.getCartDataModels = getCartDataModels;
    }

    @NonNull
    @Override
    public AddToCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_add_to_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddToCartAdapter.ViewHolder holder, int position) {
        holder.productTitle.setText(getCartDataModels.get(position).getDescription());
        holder.cartQuantityTV.setText(getCartDataModels.get(position).getTotalQuantity());
        holder.priceTV.setText(getCartDataModels.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return getCartDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productTitle;
        ImageView productImage;
        TextView cartQuantityTV;
        TextView priceTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle = itemView.findViewById(R.id.tv_rowAddToCart_productTitle);
            productImage = itemView.findViewById(R.id.iv_rowAddToCart_productImage);
            cartQuantityTV = itemView.findViewById(R.id.tv_rowAddToCart_myCartQuantity);
            priceTV = itemView.findViewById(R.id.tv_rowAddToCart_price);
        }
    }
}
