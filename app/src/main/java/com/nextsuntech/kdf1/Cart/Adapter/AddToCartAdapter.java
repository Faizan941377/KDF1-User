package com.nextsuntech.kdf1.Cart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.JsonIOException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Model.CheckOutDataModel;
import com.nextsuntech.kdf1.Model.GetCartDataModel;
import com.nextsuntech.kdf1.Model.LoginDataModel;
import com.nextsuntech.kdf1.Network.RetrofitClient;
import com.nextsuntech.kdf1.Order.OrderActivity;
import com.nextsuntech.kdf1.R;
import com.nextsuntech.kdf1.Response.CheckOutResponse;
import com.nextsuntech.kdf1.Response.DeleteCartProductResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

import static android.content.ContentValues.TAG;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {

    Context mContext;
    List<GetCartDataModel> getCartDataModelList;
    TextView addToCartTotalTV;
    TextView totalItemTV;
    TextView emptyRecyclerViewTV;
    RelativeLayout checkOutBT;


    public AddToCartAdapter(Context mContext, List<GetCartDataModel> getCartDataModelList, TextView addToCartTotalTV, RelativeLayout checkOutBT, TextView totalItemTV, TextView emptyRecyclerViewTV) {
        this.mContext = mContext;
        this.getCartDataModelList = getCartDataModelList;
        this.addToCartTotalTV = addToCartTotalTV;
        this.checkOutBT = checkOutBT;
        this.totalItemTV = totalItemTV;
        this.emptyRecyclerViewTV = emptyRecyclerViewTV;
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


        if (holder.cartQuantityTV.getText().equals("1")) {
            holder.decrementTV.setEnabled(false);
        } else {
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

                int id = getCartDataModelList.get(position).getId();

                Call<DeleteCartProductResponse> call = RetrofitClient.getInstance().getApi().deleteProductByCart(id);
                call.enqueue(new Callback<DeleteCartProductResponse>() {
                    @Override
                    public void onResponse(Call<DeleteCartProductResponse> call, Response<DeleteCartProductResponse> response) {
                        DeleteCartProductResponse deleteCartProductResponse = response.body();
                        if (response.isSuccessful()) {
                            getCartDataModelList.remove(position);
                            notifyItemRemoved(position);
                            calculation();
                            qtyCalculate();
                            notifyDataSetChanged();
                            Toast.makeText(mContext, deleteCartProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(mContext, deleteCartProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteCartProductResponse> call, Throwable t) {
                        try {
                            Toast.makeText(mContext, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
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

                String status = "CheckOut";

                try {

                    JSONArray cart = new JSONArray();
                    JSONObject jsonObject1 = new JSONObject();
                    for (GetCartDataModel getCartDataModel : getCartDataModelList) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", getCartDataModel.getId());
                        jsonObject.put("ProductId", getCartDataModel.getProductId());
                        jsonObject.put("UserRegistrationId", getCartDataModel.getUserRegistrationId());
                        jsonObject.put("TotalQuantity", getCartDataModel.getTotalQuantity());
                        jsonObject.put("Status", status);
                        jsonObject.put("Price", getCartDataModel.getPrice());
                        cart.put(jsonObject);

                        jsonObject1.put("cart", cart);
                        Log.e("cart", String.valueOf(jsonObject1));

                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject1.toString());
                        Call<CheckOutResponse> call = RetrofitClient.getInstance().getApi().postOrder(requestBody);
                        call.enqueue(new Callback<CheckOutResponse>() {
                            @Override
                            public void onResponse(Call<CheckOutResponse> call, Response<CheckOutResponse> response) {
                                CheckOutResponse checkOutResponse = response.body();

                                if (response.isSuccessful()) {
                                    Toast.makeText(mContext, checkOutResponse.getMessage() + " OderId " + checkOutResponse.getAutoId(), Toast.LENGTH_SHORT).show();
                                    String orderId = String.valueOf(checkOutResponse.getAutoId());
                                    String totalPrice = addToCartTotalTV.getText().toString();
                                    String totalItems = totalItemTV.getText().toString();
                                    Intent intent = new Intent(mContext, OrderActivity.class);
                                    intent.putExtra("totalPrice", totalPrice);
                                    intent.putExtra("cartAutoId", orderId);
                                    intent.putExtra("totalItems", totalItems);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(intent);
                                } else {
                                    Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<CheckOutResponse> call, Throwable t) {
                                try {
                                    Toast.makeText(mContext, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (getCartDataModelList.size() == 0) {
            checkOutBT.setEnabled(false);
            emptyRecyclerViewTV.setVisibility(View.VISIBLE);
        } else {
            checkOutBT.setEnabled(true);
            emptyRecyclerViewTV.setVisibility(View.GONE);
        }
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

    public void qtyCalculate() {
        int totalItemSum = 0, j;
        for (j = 0; j < getCartDataModelList.size(); j++)
            totalItemSum = totalItemSum + (getCartDataModelList.get(j).getTotalQuantity());
        totalItemTV.setText(String.valueOf(totalItemSum));
    }
}
