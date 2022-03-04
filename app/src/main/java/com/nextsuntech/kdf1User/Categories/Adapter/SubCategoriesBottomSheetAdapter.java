package com.nextsuntech.kdf1User.Categories.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.kdf1User.Model.GetPricesDataModel;
import com.nextsuntech.kdf1User.Model.LoginDataModel;
import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.AddToCartResponse;
import com.nextsuntech.kdf1User.SharedPref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_sub_categories_bottom_sheet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoriesBottomSheetAdapter.ViewHolder holder, int position) {
        holder.productNameTV.setText(getPricesDataModelList.get(position).getChoice());
        holder.pricesTV.setText(getPricesDataModelList.get(position).getPrice());


        //Add to cart the items
        LoginDataModel loginDataModel = SharedPrefManager.getInstance(mContext).getSavedUsers();
        int userId = loginDataModel.getId();
        holder.addToCartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Item Added", Toast.LENGTH_SHORT).show();
                String qty = "1";
                String price = getPricesDataModelList.get(position).getPrice();
                String productId = getPricesDataModelList.get(position).getProductId();

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
                            Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
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
        return getPricesDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productNameTV;
        TextView pricesTV;
        RelativeLayout addToCartBt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productNameTV = itemView.findViewById(R.id.tv_productName);
            pricesTV = itemView.findViewById(R.id.tv_rowCategories_details_price);
            addToCartBt = itemView.findViewById(R.id.bt_rowSubCategoryBottomSheet);
        }
    }
}
