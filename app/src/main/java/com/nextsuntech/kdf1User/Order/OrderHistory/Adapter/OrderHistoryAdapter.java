package com.nextsuntech.kdf1User.Order.OrderHistory.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.kdf1User.Model.GetOrderHistoryDataModel;

import com.nextsuntech.kdf1User.Network.RetrofitClient;
import com.nextsuntech.kdf1User.Order.OrderHistoryDetail.OrderHistoryDetailActivity;
import com.nextsuntech.kdf1User.R;
import com.nextsuntech.kdf1User.Response.DeleteCartProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    Context mContext;
    List<GetOrderHistoryDataModel> getOrderHistoryDataModelList;

    public OrderHistoryAdapter(Context mContext, List<GetOrderHistoryDataModel> getOrderHistoryDataModelList) {
        this.mContext = mContext;
        this.getOrderHistoryDataModelList = getOrderHistoryDataModelList;
    }

    @NonNull
    @Override
    public OrderHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_order_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.ViewHolder holder, int position) {

        holder.statusTV.setText(getOrderHistoryDataModelList.get(position).getStatus());
        holder.orderIdTV.setText(String.valueOf(getOrderHistoryDataModelList.get(position).getCartAutoId()));
        holder.productTitleTV.setText(String.valueOf(getOrderHistoryDataModelList.get(position).getCustomerName()));
        holder.quantityTV.setText(String.valueOf(getOrderHistoryDataModelList.get(position).getTotalQuantity()));
        holder.dateTV.setText(getOrderHistoryDataModelList.get(position).getCreateAt());
        holder.totalPriceTV.setText(String.valueOf(getOrderHistoryDataModelList.get(position).getTotalPrice()));

        String status = getOrderHistoryDataModelList.get(position).getStatus();

        if (status.equals("Dispatch")) {
            holder.statusTV.setTextColor(Color.parseColor("#24B4C6"));
            holder.statusTV.setText("Dispatch");
            holder.deleteTV.setVisibility(View.GONE);
            holder.makeACallTV.setVisibility(View.VISIBLE);

        } else if (status.length() == 6) {
            holder.statusTV.setText("Order Completed");
            holder.deleteTV.setVisibility(View.GONE);
            holder.makeACallTV.setVisibility(View.VISIBLE);
            holder.statusTV.setTextColor(Color.parseColor("#2b9f4c"));
        } else if (status.length() == 8) {
            holder.statusTV.setText("Pending");
            holder.deleteTV.setVisibility(View.VISIBLE);
            holder.makeACallTV.setVisibility(View.GONE);
            holder.statusTV.setTextColor(Color.parseColor("#FF0000"));
        }

        holder.orderHistoryDetailBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderHistoryDetailActivity.class);
                intent.putExtra("CartAutoId", String.valueOf(getOrderHistoryDataModelList.get(position).getCartAutoId()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(intent);
            }
        });

        holder.deleteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog = new AlertDialog.Builder(v.getRootView().getContext())
                        .setTitle("Alert!")
                        .setMessage("Are you sure to delete this order!")
                        .setPositiveButton("Yes", null)
                        .setNegativeButton("NO", null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String cartAutoID = String.valueOf(getOrderHistoryDataModelList.get(position).getCartAutoId());

                        Call<DeleteCartProductResponse> call = RetrofitClient.getInstance().getApi().deleteOder(Integer.parseInt(cartAutoID));
                        call.enqueue(new Callback<DeleteCartProductResponse>() {
                            @Override
                            public void onResponse(Call<DeleteCartProductResponse> call, Response<DeleteCartProductResponse> response) {
                                if (response.isSuccessful()) {
                                    getOrderHistoryDataModelList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(mContext, "Order Deleted Successfully", Toast.LENGTH_SHORT).show();
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

                        Toast.makeText(mContext, cartAutoID, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        holder.makeACallTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:03159599530"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getOrderHistoryDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView statusTV;
        TextView productTitleTV;
        TextView orderIdTV;
        TextView quantityTV;
        TextView dateTV;
        TextView totalPriceTV;
        TextView deleteTV;
        TextView makeACallTV;
        LinearLayout orderHistoryDetailBT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusTV = itemView.findViewById(R.id.tv_rowOderHistory_status);
            productTitleTV = itemView.findViewById(R.id.tv_rowOderHistory_description);
            orderIdTV = itemView.findViewById(R.id.tv_rowOderHistory_orderId);
            quantityTV = itemView.findViewById(R.id.tv_rowOderHistory_quantity);
            dateTV = itemView.findViewById(R.id.tv_rowOderHistory_date);
            totalPriceTV = itemView.findViewById(R.id.tv_rowOderHistory_totalPrice);
            deleteTV = itemView.findViewById(R.id.bt_rowOrderHistoryDetail_delete);
            orderHistoryDetailBT = itemView.findViewById(R.id.bt_rowOrderHistoryDetail);
            makeACallTV = itemView.findViewById(R.id.bt_rowOrderHistoryDetail_makeACall);
        }
    }
}
