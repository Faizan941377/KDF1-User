package com.nextsuntech.kdf1User.Dashboard.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.nextsuntech.kdf1User.Categories.CategoriesDetailActivity;
import com.nextsuntech.kdf1User.Model.CategoriesDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    List<CategoriesDataModel> categoriesDataModelList;
    List<CategoriesDataModel> fetchCategoriesDataModelList;

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
        holder.descriptionTV.setText(categoriesDataModelList.get(position).getDescription());

        holder.progressBar.setVisibility(View.VISIBLE);
        String path = RetrofitClient.IMAGE_BASE_URL + categoriesDataModelList.get(position).getImage() + "";
        Glide.with(mContext).load(path)
                .listener(new RequestListener<Drawable>() {
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
                })
                .into(holder.menuIV);


        holder.categoriesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_row_categories:
                        Intent intent = new Intent(mContext.getApplicationContext(), CategoriesDetailActivity.class);
                        Toast.makeText(mContext, "" + categoriesDataModelList.get(position).getId(), Toast.LENGTH_SHORT).show();
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

    public Filter getFilter() {
        return filter;
    }

    public Filter filter = new Filter() {
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
        RelativeLayout productDetailBT;
        TextView productName;
        TextView descriptionTV;
        ImageView menuIV;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriesBT = itemView.findViewById(R.id.bt_row_categories);
            productName = itemView.findViewById(R.id.tv_productName);
            menuIV = itemView.findViewById(R.id.iv_pizza);
            progressBar = itemView.findViewById(R.id.pb_rowCategory_image);
            descriptionTV = itemView.findViewById(R.id.tv_rowCategories_details_description);

        }
    }
}
