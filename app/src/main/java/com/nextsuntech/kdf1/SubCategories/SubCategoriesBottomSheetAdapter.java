package com.nextsuntech.kdf1.SubCategories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.kdf1.Model.GetPricesDataModel;
import com.nextsuntech.kdf1.R;

import java.util.List;

public class SubCategoriesBottomSheetAdapter extends RecyclerView.Adapter<SubCategoriesBottomSheetAdapter.ViewHolder> {

    Context mContext;
    List<GetPricesDataModel> getPricesDataModelList;

    public SubCategoriesBottomSheetAdapter(Context mContext, List<GetPricesDataModel> getPricesDataModelList) {
        this.mContext = mContext;
        this.getPricesDataModelList = getPricesDataModelList;
    }

    @NonNull
    @Override
    public SubCategoriesBottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_sub_categories_bottom_sheet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoriesBottomSheetAdapter.ViewHolder holder, int position) {
        holder.productNameTV.setText(getPricesDataModelList.get(position).getChoice());
       // holder.pricesTV.setText(getPricesDataModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return getPricesDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productNameTV;
        TextView pricesTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productNameTV = itemView.findViewById(R.id.tv_productName);
            pricesTV = itemView.findViewById(R.id.tv_rowCategories_details_price);
        }
    }
}
