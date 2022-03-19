package com.nextsuntech.kdf1User.Order.OrderHistory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.kdf1User.Model.GetOrderHistoryDataModel;

import com.nextsuntech.kdf1User.Order.OrderHistoryDetail.OrderHistoryDetailActivity;
import com.nextsuntech.kdf1User.R;

import java.util.List;

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

        if (status.length() == 6) {
            holder.statusTV.setText("Order Completed");
            holder.statusTV.setTextColor(Color.parseColor("#2b9f4c"));
        } else if (status.length() == 8) {
            holder.statusTV.setText("Pending");
            holder.statusTV.setTextColor(Color.parseColor("#FF0000"));
        } else if (status.length() == 4) {
            holder.statusTV.setText("Cart pending");
            holder.statusTV.setTextColor(Color.parseColor("#2b9f4c"));
        }

        holder.orderHistoryDetailBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderHistoryDetailActivity.class);
                intent.putExtra("CartAutoId",String.valueOf(getOrderHistoryDataModelList.get(position).getCartAutoId()));
                Toast.makeText(mContext, String.valueOf( getOrderHistoryDataModelList.get(position).getCartAutoId()), Toast.LENGTH_SHORT).show();
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
        LinearLayout orderHistoryDetailBT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statusTV = itemView.findViewById(R.id.tv_rowOderHistory_status);
            productTitleTV = itemView.findViewById(R.id.tv_rowOderHistory_description);
            orderIdTV = itemView.findViewById(R.id.tv_rowOderHistory_orderId);
            quantityTV = itemView.findViewById(R.id.tv_rowOderHistory_quantity);
            dateTV = itemView.findViewById(R.id.tv_rowOderHistory_date);
            totalPriceTV = itemView.findViewById(R.id.tv_rowOderHistory_totalPrice);
            orderHistoryDetailBT = itemView.findViewById(R.id.bt_rowOrderHistoryDetail);
        }
    }
}
