package com.nextsuntech.kdf1User.Order.OrderHistoryDetail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.kdf1User.Model.GetOrderHistoryDetailDataModel;
import com.nextsuntech.kdf1User.R;

import java.util.List;

public class OrderHistoryDetailAdapter extends RecyclerView.Adapter<OrderHistoryDetailAdapter.ViewHolder> {

    Context mContext;
    List<GetOrderHistoryDetailDataModel> getOrderHistoryDetailDataModels;

    public OrderHistoryDetailAdapter(Context mContext, List<GetOrderHistoryDetailDataModel> getOrderHistoryDetailDataModels) {
        this.mContext = mContext;
        this.getOrderHistoryDetailDataModels = getOrderHistoryDetailDataModels;
    }

    @NonNull
    @Override
    public OrderHistoryDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_order_histroy_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryDetailAdapter.ViewHolder holder, int position) {

        holder.productTitleTV.setText(getOrderHistoryDetailDataModels.get(position).getCustomerName());
    }

    @Override
    public int getItemCount() {
        return getOrderHistoryDetailDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productTitleTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitleTV = itemView.findViewById(R.id.tv_rowOderHistory_description);
        }
    }
}
