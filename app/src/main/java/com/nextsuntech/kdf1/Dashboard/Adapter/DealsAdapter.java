package com.nextsuntech.kdf1.Dashboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.kdf1.R;


public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder> {

    Context mContext;

    public DealsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_deals, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.subIitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* BottomSheetDialog  builder = new BottomSheetDialog(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.bottom_sheet_layout, null);

               //
                RecyclerView recyclerView;
                Adapter adapter;


                recyclerView = dialogView.findViewById(R.id.bottom_sheet_rv);

                adapter = new Adapter(mContext);
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getRootView().getContext(), RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(adapter);


                //builder.setView(dialogView);
                builder.setContentView(dialogView);
                builder.setCancelable(true);
                builder.show();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView subIitems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subIitems = itemView.findViewById(R.id.iv_subItems);
        }
    }

}
