package com.example.productmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.productmanagement.R;

public class StatisticsActivity extends AppCompatActivity {

    private Button todayButton, weekButton, monthButton, calendarButton;
    private TextView totalOrdersTextView, abandonmentRateTextView, potentialRevenueTextView, averageValueTextView;
    private LinearLayout customerLayout, productLayout, cartLayout, orderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // Khởi tạo các thành phần giao diện
        todayButton = findViewById(R.id.today_button);
        weekButton = findViewById(R.id.week_button);
        monthButton = findViewById(R.id.month_button);
        ImageButton calendarButton = findViewById(R.id.calendar_button);
        totalOrdersTextView = findViewById(R.id.total_orders_text_view);
        abandonmentRateTextView = findViewById(R.id.abandonment_rate_text_view);
        potentialRevenueTextView = findViewById(R.id.potential_revenue_text_view);
        averageValueTextView = findViewById(R.id.average_value_text_view);
        
        customerLayout = findViewById(R.id.customer_layout);
        productLayout = findViewById(R.id.product_layout);
        cartLayout = findViewById(R.id.cart_layout);
        orderLayout = findViewById(R.id.order_layout);

        // Thiết lập dữ liệu mặc định (thống kê theo tuần)
        displayWeeklyStatistics();

        // Thiết lập sự kiện click cho các nút thời gian
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDailyStatistics();
            }
        });

        weekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayWeeklyStatistics();
            }
        });

        monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayMonthlyStatistics();
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trong thực tế, bạn sẽ mở màn hình chọn ngày
                displayCustomStatistics();
            }
        });

        // Thiết lập sự kiện click cho các menu
        setupBottomNavigation();
    }

    private void displayDailyStatistics() {
        // Hiển thị dữ liệu thống kê theo ngày
        totalOrdersTextView.setText("42");
        abandonmentRateTextView.setText("3.75%");
        potentialRevenueTextView.setText("78.5 Tr");
        averageValueTextView.setText("3.2 Tr");
        
        // Cập nhật trạng thái nút
        updateButtonStates(todayButton);
    }

    private void displayWeeklyStatistics() {
        // Hiển thị dữ liệu thống kê theo tuần
        totalOrdersTextView.setText("168");
        abandonmentRateTextView.setText("5.95%");
        potentialRevenueTextView.setText("297.5 Tr");
        averageValueTextView.setText("12.6 Tr");
        
        // Cập nhật trạng thái nút
        updateButtonStates(weekButton);
    }

    private void displayMonthlyStatistics() {
        // Hiển thị dữ liệu thống kê theo tháng
        totalOrdersTextView.setText("720");
        abandonmentRateTextView.setText("7.25%");
        potentialRevenueTextView.setText("1.25 Tỷ");
        averageValueTextView.setText("48.3 Tr");
        
        // Cập nhật trạng thái nút
        updateButtonStates(monthButton);
    }

    private void displayCustomStatistics() {
        // Hiển thị dữ liệu thống kê tùy chỉnh
        totalOrdersTextView.setText("350");
        abandonmentRateTextView.setText("6.50%");
        potentialRevenueTextView.setText("620.8 Tr");
        averageValueTextView.setText("25.7 Tr");
        
        // Cập nhật trạng thái nút
        updateButtonStates(calendarButton);
    }

    private void updateButtonStates(Button activeButton) {
        // Đặt lại trạng thái của tất cả các nút
        todayButton.setBackgroundResource(R.drawable.button_inactive);
        weekButton.setBackgroundResource(R.drawable.button_inactive);
        monthButton.setBackgroundResource(R.drawable.button_inactive);
        calendarButton.setBackgroundResource(R.drawable.button_inactive);
        
        // Đặt trạng thái cho nút đang hoạt động
        activeButton.setBackgroundResource(R.drawable.button_active);
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
                Intent intent = new Intent(StatisticsActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đã ở màn hình Giỏ hàng/Thống kê
            }
        });

        orderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

