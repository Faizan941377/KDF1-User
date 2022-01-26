package com.nextsuntech.kdf1.Dashboard.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslCertificate;
import android.os.Bundle;
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

import com.nextsuntech.kdf1.Categories.CategoriesDetailActivity;
import com.nextsuntech.kdf1.Model.CategoriesDataModel;
import com.nextsuntech.kdf1.R;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.namespace.QName;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> implements Filterable {

    Context mContext;
    private List<CategoriesDataModel> categoriesDataModelList;
    private List<CategoriesDataModel> fetchCategoriesDataModelList;


    public CategoriesAdapter(Context mContext, List<CategoriesDataModel> categoriesDataModelList) {
        this.mContext = mContext;
        this.categoriesDataModelList = categoriesDataModelList;
        fetchCategoriesDataModelList = new ArrayList<>();
        fetchCategoriesDataModelList.addAll(categoriesDataModelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(categoriesDataModelList.get(position).getTitle());
        holder.priceTV.setText(categoriesDataModelList.get(position).getStatus());
       /* String path = RetrofitClient.IMAGE_BASE_URL + categoriesDetailsDataModelList.get(position).getImage() + "";
        Glide.with(mContext).load(path).centerCrop().into(holder.menuIV);*/


        holder.categoriesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_row_categories:
                        Intent intent = new Intent(mContext.getApplicationContext(), CategoriesDetailActivity.class);
                        Toast.makeText(mContext, "" +categoriesDataModelList.get(position).getId(), Toast.LENGTH_SHORT).show();
                        intent.putExtra("id", categoriesDataModelList.get(position).getId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.getApplicationContext().startActivity(intent);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesDataModelList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CategoriesDataModel> filterList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterList.addAll(fetchCategoriesDataModelList);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for (CategoriesDataModel dataItem : fetchCategoriesDataModelList) {
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
            categoriesDataModelList.clear();
            categoriesDataModelList.addAll((Collection<? extends CategoriesDataModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout categoriesBT;
        TextView productName;
        TextView priceTV;
        ImageView menuIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriesBT = itemView.findViewById(R.id.bt_row_categories);
            productName = itemView.findViewById(R.id.tv_productName);
            menuIV = itemView.findViewById(R.id.iv_pizza);
            priceTV = itemView.findViewById(R.id.tv_rowCategories_price);

        }
    }
}
