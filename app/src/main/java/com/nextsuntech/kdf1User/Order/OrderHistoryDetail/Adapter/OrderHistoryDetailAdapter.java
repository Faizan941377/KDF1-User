package com.nextsuntech.kdf1User.Order.OrderHistoryDetail.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.nextsuntech.kdf1User.Model.GetOrderHistoryDetailDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderHistoryDetailAdapter extends RecyclerView.Adapter<OrderHistoryDetailAdapter.ViewHolder> {

    Context mContext;
    List<GetOrderHistoryDetailDataModel> getOrderHistoryDetailDataModels;

    public OrderHistoryDetailAdapter(Context mContext, List<GetOrderHistoryDetailDataModel> getOrderHistoryDetailDataModels) {
        this.mContext = mContext;
        this.getOrderHistoryDetailDataModels = getOrderHistoryDetailDataModels;
    }

    @NonNull
    @Override
    public OrderHistoryDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_order_histroy_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryDetailAdapter.ViewHolder holder, int position) {

        holder.orderIdTV.setText(getOrderHistoryDetailDataModels.get(position).getId());
        holder.productTitleTV.setText(getOrderHistoryDetailDataModels.get(position).getProductName());
        holder.quantityTV.setText(getOrderHistoryDetailDataModels.get(position).getTotalQuantity());
        holder.descriptionTV.setText(getOrderHistoryDetailDataModels.get(position).getStatus());
        holder.priceTV.setText(getOrderHistoryDetailDataModels.get(position).getPrice());
        holder.dateTV.setText(getOrderHistoryDetailDataModels.get(position).getDate());


        //progress bar
        holder.imagePB.setVisibility(View.VISIBLE);
        String path = RetrofitClient.IMAGE_BASE_URL + getOrderHistoryDetailDataModels.get(position).getImageName().getImages();
        Glide.with(mContext).load(path)
                .listener(new RequestListener<Drawable>() {
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
    }

    @Override
    public int getItemCount() {
        return getOrderHistoryDetailDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView productImage;
        ProgressBar imagePB;
        TextView orderIdTV;
        TextView productTitleTV;
        TextView quantityTV;
        TextView descriptionTV;
        TextView priceTV;
        TextView dateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderIdTV = itemView.findViewById(R.id.tv_rowOderHistory_orderId);
            productImage = itemView.findViewById(R.id.iv_rowOrderHistoryDetail_Image);
            imagePB = itemView.findViewById(R.id.pb_OderHistoryDetail);
            productTitleTV = itemView.findViewById(R.id.tv_rowOderHistory_description);
            quantityTV = itemView.findViewById(R.id.tv_rowOderHistoryDetail_quantity);
            descriptionTV = itemView.findViewById(R.id.tv_rowOderHistoryDetail_description);
            priceTV = itemView.findViewById(R.id.tv_rowOderHistory_totalPrice);
            dateTV = itemView.findViewById(R.id.tv_rowOderHistoryDetail_date);
        }
    }
}
