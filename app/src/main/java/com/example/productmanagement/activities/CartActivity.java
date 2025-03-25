package com.example.productmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.productmanagement.R;

public class CartActivity extends AppCompatActivity {

    private LinearLayout customerLayout, productLayout, cartLayout, orderLayout;
    private LinearLayout statisticsLayout;
    private TextView statisticsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Khởi tạo các thành phần giao diện
        customerLayout = findViewById(R.id.customer_layout);
        productLayout = findViewById(R.id.product_layout);
        cartLayout = findViewById(R.id.cart_layout);
        orderLayout = findViewById(R.id.order_layout);
        statisticsLayout = findViewById(R.id.statistics_layout);
        statisticsTextView = findViewById(R.id.textViewStatistics);

        // Thiết lập sự kiện click cho layout thống kê
        statisticsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });

        // Thiết lập sự kiện click cho các menu
        setupBottomNavigation();
    }

    private void openStatisticsActivity() {
        Intent intent = new Intent(CartActivity.this, StatisticsActivity.class);
        startActivity(intent);
    }

    private void setupBottomNavigation() {
        customerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nhấn vào Khách hàng
            }
        });

        productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đã ở màn hình Giỏ hàng
            }
        });

        orderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

