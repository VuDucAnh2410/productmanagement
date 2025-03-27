package com.example.productmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.activities.ProductDetailActivity;
import com.example.productmanagement.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.categoryTextView.setText(product.getCategory());
        holder.nameTextView.setText(product.getName());
        holder.stockTextView.setText("Còn hàng (" + product.getStock() + ")");

        // Trong thực tế, bạn sẽ tải ảnh sản phẩm từ URL hoặc tài nguyên
        holder.productImageView.setImageResource(R.drawable.product_placeholder);

        // Thiết lập sự kiện click cho tên sản phẩm
        holder.nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductDetail(product);
            }
        });

        // Thiết lập sự kiện click cho item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductDetail(product);
            }
        });

        // Thiết lập sự kiện click cho nút chỉnh sửa
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product_code", product.getCode());
                intent.putExtra("product_name", product.getName());
                intent.putExtra("edit_mode", true);
                context.startActivity(intent);
            }
        });

        // Thiết lập sự kiện click cho nút xóa
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trong thực tế, bạn sẽ xóa sản phẩm khỏi cơ sở dữ liệu
                productList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    private void openProductDetail(Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("product_code", product.getCode());
        intent.putExtra("product_name", product.getName());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        TextView categoryTextView, nameTextView, stockTextView;
        ImageButton favoriteButton, editButton, deleteButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImageView = itemView.findViewById(R.id.product_image_view);
            categoryTextView = itemView.findViewById(R.id.category_text_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            stockTextView = itemView.findViewById(R.id.stock_text_view);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}

