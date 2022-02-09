package com.nextsuntech.kdf1.Categories.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nextsuntech.kdf1.Cart.AddToCartActivity;
import com.nextsuntech.kdf1.Model.GetProductDataModel;
import com.nextsuntech.kdf1.Model.LoginDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.ProductDetails.ProductDetailsActivity;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.AddToCartResponse;
import com.nextsuntech.kdf1.SharedPref.SharedPrefManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesDetailsAdapter extends RecyclerView.Adapter<CategoriesDetailsAdapter.ViewHolder> implements Filterable {

    Context mContext;
    List<GetProductDataModel> productDataModelList;
    List<GetProductDataModel> fetchProductDataModelList;


    public CategoriesDetailsAdapter(Context mContext, List<GetProductDataModel> productDataModelList) {
        this.mContext = mContext;
        this.productDataModelList = productDataModelList;
        fetchProductDataModelList = new ArrayList<>();
        fetchProductDataModelList.addAll(productDataModelList);
    }

    @NonNull
    @Override
    public CategoriesDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_categories_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesDetailsAdapter.ViewHolder holder, int position) {

        holder.productName.setText(productDataModelList.get(position).getTitle());
        holder.priceTV.setText(productDataModelList.get(position).getPrice());
        holder.descriptionTV.setText(productDataModelList.get(position).getDescription());
        holder.stockTV.setText(productDataModelList.get(position).getStockstatus());


        if (holder.stockTV.length() == 8) {
            holder.stockTV.setTextColor(Color.parseColor("#2b9f4c"));
        } else if (holder.stockTV.length() == 12) {
            holder.stockTV.setTextColor(Color.parseColor("#FF0000"));
        }


        //progress bar
        holder.imageDetailPB.setVisibility(View.VISIBLE);
        String path = RetrofitClient.IMAGE_BASE_URL + productDataModelList.get(position).getImageName().get(0).getImages();
        Glide.with(mContext).load(path)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.imageDetailPB.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.imageDetailPB.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.detailPizzaIV);


//send data to product Details activity
        holder.productDetailsBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                intent.putExtra("description", productDataModelList.get(position).getDescription());
                intent.putExtra("productTitle", productDataModelList.get(position).getTitle());
                intent.putExtra("price",productDataModelList.get(position).getPrice());
                intent.putExtra("productId",productDataModelList.get(position).getId());
                intent.putExtra("img_url", RetrofitClient.IMAGE_BASE_URL + productDataModelList.get(position).getImageName().get(0).getImages());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(intent);
            }
        });


        LoginDataModel loginDataModel = SharedPrefManager.getInstance(mContext).getSavedUsers();
        int userId = loginDataModel.getId();
        //send data Add to cart activity
        holder.addToCartIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty = "1";
                String price = productDataModelList.get(position).getPrice();
                String productId = productDataModelList.get(position).getId();
                Call<AddToCartResponse> call = RetrofitClient.getInstance().getApi().AddToCart(productId, userId, qty, price);
                call.enqueue(new Callback<AddToCartResponse>() {
                    @Override
                    public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                        AddToCartResponse addToCartResponse = response.body();
                        if (response.isSuccessful()) {
                            Toast.makeText(mContext, addToCartResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                        try {
                            Toast.makeText(mContext, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return productDataModelList.size();
    }

    public Filter getFilter() {
        return filter;
    }

    public Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<GetProductDataModel> filterList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterList.addAll(fetchProductDataModelList);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for (GetProductDataModel dataItem : fetchProductDataModelList) {
                    if (dataItem.getTitle().toLowerCase().contains(filter)) {
                        filterList.add(dataItem);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productDataModelList.clear();
            productDataModelList.addAll((Collection<? extends GetProductDataModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView priceTV;
        TextView descriptionTV;
        TextView stockTV;
        ImageView detailPizzaIV;
        ImageView addToCartIV;
        ProgressBar imageDetailPB;
        RelativeLayout productDetailsBT;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.tv_productName);
            priceTV = itemView.findViewById(R.id.tv_rowCategories_details_price);
            detailPizzaIV = itemView.findViewById(R.id.iv_rowCategoryDetailPizza);
            imageDetailPB = itemView.findViewById(R.id.pb_rowCategoryDetail_image);
            descriptionTV = itemView.findViewById(R.id.tv_rowCategories_details_description);
            stockTV = itemView.findViewById(R.id.tv_rowCategories_details_status);
            productDetailsBT = itemView.findViewById(R.id.bt_rowCategoryDetails_productDetails);
            addToCartIV = itemView.findViewById(R.id.iv_rowCategoryDetail_cart);

        }
    }
}
