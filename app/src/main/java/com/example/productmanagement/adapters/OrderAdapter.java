package com.example.productmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.activities.OrderDetailActivity;
import com.example.productmanagement.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        
        holder.orderIdTextView.setText(order.getId());
        holder.totalTextView.setText(String.format("%,.0f", order.getTotal()));
        holder.dateTextView.setText(order.getDate());
        holder.statusTextView.setText(order.getStatus());
        
        // Thiết lập màu sắc cho trạng thái
        if ("Chưa xác nhận".equals(order.getStatus())) {
            holder.statusTextView.setTextColor(context.getResources().getColor(R.color.status_pending));
        } else if ("Xác nhận".equals(order.getStatus())) {
            holder.statusTextView.setTextColor(context.getResources().getColor(R.color.status_confirmed));
        } else if ("Đang giao".equals(order.getStatus())) {
            holder.statusTextView.setTextColor(context.getResources().getColor(R.color.status_shipping));
        } else if ("Hủy đơn".equals(order.getStatus())) {
            holder.statusTextView.setTextColor(context.getResources().getColor(R.color.status_cancelled));
        }
        
        // Thiết lập sự kiện click cho item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("order_id", order.getId());
                context.startActivity(intent);
            }
        });
        
        // Thiết lập sự kiện click cho nút chỉnh sửa
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("order_id", order.getId());
                intent.putExtra("edit_mode", true);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        
        TextView orderIdTextView, totalTextView, dateTextView, statusTextView;
        ImageButton editButton;
        
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            
            orderIdTextView = itemView.findViewById(R.id.order_id_text_view);
            totalTextView = itemView.findViewById(R.id.total_text_view);
            dateTextView = itemView.findViewById(R.id.date_text_view);
            statusTextView = itemView.findViewById(R.id.status_text_view);
            editButton = itemView.findViewById(R.id.edit_button);
        }
    }
}

