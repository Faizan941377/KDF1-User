package com.nextsuntech.kdf1User.Order.OrderHistory.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nextsuntech.kdf1User.Model.GetCartDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    Context mContext;
    List<GetCartDataModel> getCartDataModelList;

    public OrderHistoryAdapter(Context mContext, List<GetCartDataModel> getCartDataModelList) {
        this.mContext = mContext;
        this.getCartDataModelList = getCartDataModelList;
    }

    @NonNull
    @Override
    public OrderHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.row_orderhistory,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.ViewHolder holder, int position) {

        holder.statusTV.setText(getCartDataModelList.get(position).getStatus());

        String status = getCartDataModelList.get(position).getStatus();

        if (status.equals("Cart")){
            holder.statusTV.setText("Your Order is pending");

        }else if (status.equals("CheckOut")){
            holder.statusTV.setText("Pending");

        }

        String path = RetrofitClient.IMAGE_BASE_URL + getCartDataModelList.get(position).imageName.getImages();
        Glide.with(mContext).load(path).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return getCartDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView statusTV;
        ImageView productImage;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusTV = itemView.findViewById(R.id.tv_rowOderHistory_status);
            productImage = itemView.findViewById(R.id.iv_rowOrderHistory_Image);
            progressBar = itemView.findViewById(R.id.pb_OderHistory);
        }
    }
}