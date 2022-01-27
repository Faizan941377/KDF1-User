package com.nextsuntech.kdf1.Categories.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nextsuntech.kdf1.Categories.CategoriesDetailActivity;
import com.nextsuntech.kdf1.Model.CategoriesDataModel;
import com.nextsuntech.kdf1.Model.GetProductDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.Network.WebServices;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.GetProductResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_categories_details,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesDetailsAdapter.ViewHolder holder, int position) {
        holder.productName.setText(productDataModelList.get(position).getTitle());
        holder.priceTV.setText(productDataModelList.get(position).getPrice());

        String  path = RetrofitClient.IMAGE_BASE_URL1 + productDataModelList.get(position).getImages();
        Glide.with(mContext).load(path).into(holder.detailPizzaIV);

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
        ImageView detailPizzaIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.tv_productName);
            priceTV = itemView.findViewById(R.id.tv_rowCategories_details_price);
            detailPizzaIV = itemView.findViewById(R.id.iv_rowCategoryDetailPizza);
        }
    }
}
