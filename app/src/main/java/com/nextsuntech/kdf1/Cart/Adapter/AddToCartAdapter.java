package com.nextsuntech.kdf1.Cart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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

        holder.priceTV.setText(getCartDataModels.get(position).getPrice());
        holder.productTitle.setText(getCartDataModels.get(position).getDescription());
        holder.cartQuantityTV.setText(getCartDataModels.get(position).getTotalQuantity());

        String path = RetrofitClient.IMAGE_BASE_URL + getCartDataModels.get(position).imageName.getImages();
        Glide.with(mContext).load(path).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.imagePB.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.imagePB.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.productImage);

        holder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getCartDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productTitle;
        ImageView productImage;
        ImageView deleteIV;
        TextView cartQuantityTV;
        TextView priceTV;
        TextView incrementTV;
        TextView decrementTV;
        ProgressBar imagePB;
        int count = 0;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle = itemView.findViewById(R.id.tv_rowAddToCart_productTitle);
            productImage = itemView.findViewById(R.id.iv_rowAddToCart_productImage);
            cartQuantityTV = itemView.findViewById(R.id.tv_rowAddToCart_myCartQuantity);
            priceTV = itemView.findViewById(R.id.tv_rowAddToCart_price);
            imagePB = itemView.findViewById(R.id.pb_rowAddToCart);
            incrementTV = itemView.findViewById(R.id.tv_rowAddToCart_Increment);
            decrementTV = itemView.findViewById(R.id.tv_rowAddToCart_decrement);
            deleteIV = itemView.findViewById(R.id.iv_rowAddToCart_delete);


            incrementTV.setOnClickListener(this);
            decrementTV.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_rowAddToCart_Increment:
                    count ++;
                    cartQuantityTV.setText(""+count);
                    break;
                case R.id.tv_rowAddToCart_decrement:
                    if (count<=1) count = 1;
                    else
                        count--;
                    cartQuantityTV.setText(""+count);
            }
        }
    }
}
