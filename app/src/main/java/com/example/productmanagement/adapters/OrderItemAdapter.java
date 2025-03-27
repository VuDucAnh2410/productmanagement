package com.example.productmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.models.OrderItem;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private Context context;
    private List<OrderItem> orderItemList;
    private boolean readOnly = false; // Thêm biến để kiểm soát chế độ chỉ đọc

    public OrderItemAdapter(Context context, List<OrderItem> orderItemList) {
        this.context = context;
        this.orderItemList = orderItemList;
    }

    // Constructor mới với tham số readOnly
    public OrderItemAdapter(Context context, List<OrderItem> orderItemList, boolean readOnly) {
        this.context = context;
        this.orderItemList = orderItemList;
        this.readOnly = readOnly;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_product, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItem orderItem = orderItemList.get(position);

        holder.productNameTextView.setText(orderItem.getProduct().getName());
        holder.categoryTextView.setText(orderItem.getProduct().getCategory());
        holder.priceTextView.setText(String.format("%,.0f đ", orderItem.getProduct().getSellingPrice()));
        holder.quantityTextView.setText(String.valueOf(orderItem.getQuantity()));

        // Trong thực tế, bạn sẽ tải ảnh sản phẩm từ URL hoặc tài nguyên
        holder.productImageView.setImageResource(R.drawable.product_placeholder);

        // Ẩn các nút điều chỉnh số lượng và xóa nếu ở chế độ chỉ đọc
        if (readOnly) {
            holder.decreaseButton.setVisibility(View.GONE);
            holder.increaseButton.setVisibility(View.GONE);
            holder.deleteButton.setVisibility(View.GONE);
        } else {
            holder.decreaseButton.setVisibility(View.VISIBLE);
            holder.increaseButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setVisibility(View.VISIBLE);

            // Thiết lập sự kiện click cho nút giảm số lượng
            holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = orderItem.getQuantity();
                    if (quantity > 1) {
                        orderItem.setQuantity(quantity - 1);
                        notifyItemChanged(position);
                    }
                }
            });

            // Thiết lập sự kiện click cho nút tăng số lượng
            holder.increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = orderItem.getQuantity();
                    orderItem.setQuantity(quantity + 1);
                    notifyItemChanged(position);
                }
            });

            // Thiết lập sự kiện click cho nút xóa
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderItemList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        TextView productNameTextView, categoryTextView, priceTextView, quantityTextView;
        ImageButton decreaseButton, increaseButton, deleteButton;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);

            productImageView = itemView.findViewById(R.id.product_image_view);
            productNameTextView = itemView.findViewById(R.id.product_name_text_view);
            categoryTextView = itemView.findViewById(R.id.category_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            quantityTextView = itemView.findViewById(R.id.quantity_text_view);
            decreaseButton = itemView.findViewById(R.id.decrease_button);
            increaseButton = itemView.findViewById(R.id.increase_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}

