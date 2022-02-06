package com.nextsuntech.kdf1.Cart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.nextsuntech.kdf1.Cart.AddToCartActivity;
import com.nextsuntech.kdf1.Dashboard.DashboardActivity;
import com.nextsuntech.kdf1.Model.GetCartDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.Order.OrderActivity;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.DeleteCartProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {

    Context mContext;
    List<GetCartDataModel> getCartDataModelList;
    TextView addToCartTotalTV;
    TextView totalItemTV;
    RelativeLayout checkOutBT;

    public AddToCartAdapter(Context mContext, List<GetCartDataModel> getCartDataModelList, TextView addToCartTotalTV, RelativeLayout checkOutBT, TextView totalItemTV) {
        this.mContext = mContext;
        this.getCartDataModelList = getCartDataModelList;
        this.addToCartTotalTV = addToCartTotalTV;
        this.checkOutBT = checkOutBT;
        this.totalItemTV = totalItemTV;
    }

    @NonNull
    @Override
    public AddToCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_add_to_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddToCartAdapter.ViewHolder holder, int position) {
        holder.priceTV.setText(String.valueOf(getCartDataModelList.get(position).getPrice()));
        holder.productTitle.setText(getCartDataModelList.get(position).getDescription());
        holder.cartQuantityTV.setText(String.valueOf(getCartDataModelList.get(position).getTotalQuantity()));


        if (holder.cartQuantityTV.getText().equals("1")){
            holder.decrementTV.setEnabled(false);
        }else {
            holder.decrementTV.setEnabled(true);
        }

        String path = RetrofitClient.IMAGE_BASE_URL + getCartDataModelList.get(position).imageName.getImages();
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

                int id = Integer.parseInt(getCartDataModelList.get(position).getId());

                Call<DeleteCartProductResponse> call = RetrofitClient.getInstance().getApi().deleteProductByCart(id);
                call.enqueue(new Callback<DeleteCartProductResponse>() {
                    @Override
                    public void onResponse(Call<DeleteCartProductResponse> call, Response<DeleteCartProductResponse> response) {
                        DeleteCartProductResponse deleteCartProductResponse = response.body();
                        if (response.isSuccessful()){
                            getCartDataModelList.remove(position);
                            notifyItemRemoved(position);
                            calculation();
                            qtyCalculate();
                            Toast.makeText(mContext, deleteCartProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            //Toast.makeText(mContext, deleteCartProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteCartProductResponse> call, Throwable t) {
                        try {
                            Toast.makeText(mContext, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        holder.incrementTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = getCartDataModelList.get(position).getTotalQuantity();
                qty++;
                getCartDataModelList.get(position).setTotalQuantity(qty);
                notifyDataSetChanged();
                calculation();
                qtyCalculate();
            }
        });

        holder.decrementTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = getCartDataModelList.get(position).getTotalQuantity();
                qty--;
                getCartDataModelList.get(position).setTotalQuantity(qty);
                notifyDataSetChanged();
                calculation();
                qtyCalculate();
            }
        });

        checkOutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalPrice = addToCartTotalTV.getText().toString();
                String totalItems = totalItemTV.getText().toString();
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("totalPrice",totalPrice);
                intent.putExtra("cartAutoId",getCartDataModelList.get(position).getCartAutoId());
                intent.putExtra("totalItems",totalItems);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return getCartDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productTitle;
        ImageView productImage;
        ImageView deleteIV;
        TextView cartQuantityTV;
        TextView priceTV;
        TextView incrementTV;
        TextView decrementTV;
        ProgressBar imagePB;


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
        }
    }

    public void calculation() {
        int sum = 0, i;
        for (i = 0; i < getCartDataModelList.size(); i++)
            sum = sum + (getCartDataModelList.get(i).getPrice() * getCartDataModelList.get(i).getTotalQuantity());
        addToCartTotalTV.setText(String.valueOf(sum));
    }

    public void qtyCalculate(){
        int totalItemSum=0,j;
        for (j=0;j<getCartDataModelList.size();j++)
            totalItemSum = totalItemSum+(getCartDataModelList.get(j).getTotalQuantity());
        totalItemTV.setText(String.valueOf(totalItemSum));
    }
}
