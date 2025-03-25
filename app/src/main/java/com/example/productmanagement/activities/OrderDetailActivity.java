package com.example.productmanagement.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.adapters.OrderItemAdapter;
import com.example.productmanagement.models.Order;
import com.example.productmanagement.models.OrderItem;
import com.example.productmanagement.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView orderIdTextView, orderDateTextView, customerNameTextView, customerPhoneTextView,
                     itemCountTextView, totalTextView, statusTextView, waitingTimeTextView;
    private RecyclerView orderItemsRecyclerView;
    private Button deleteButton, saveButton;
    private ImageButton backButton;
    private OrderItemAdapter adapter;
    private List<OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Khởi tạo các thành phần giao diện
        orderIdTextView = findViewById(R.id.order_id_text_view);
        orderDateTextView = findViewById(R.id.order_date_text_view);
        customerNameTextView = findViewById(R.id.customer_name_text_view);
        customerPhoneTextView = findViewById(R.id.customer_phone_text_view);
        itemCountTextView = findViewById(R.id.item_count_text_view);
        totalTextView = findViewById(R.id.total_text_view);
        statusTextView = findViewById(R.id.status_text_view);
        waitingTimeTextView = findViewById(R.id.waiting_time_text_view);
        orderItemsRecyclerView = findViewById(R.id.order_items_recycler_view);
        deleteButton = findViewById(R.id.delete_button);
        saveButton = findViewById(R.id.save_button);
        backButton = findViewById(R.id.back_button);

        // Thiết lập RecyclerView
        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Lấy dữ liệu đơn hàng từ Intent
        if (getIntent().hasExtra("order_id")) {
            String orderId = getIntent().getStringExtra("order_id");
            
            // Trong thực tế, bạn sẽ truy vấn cơ sở dữ liệu để lấy thông tin đơn hàng
            // Đối với ứng dụng mẫu này, chúng ta sẽ tạo dữ liệu mẫu
            Order order = getSampleOrder(orderId);
            orderItems = getSampleOrderItems();
            
            if (order != null) {
                displayOrderDetails(order);
            }
        }
        
        // Thiết lập adapter
        adapter = new OrderItemAdapter(this, orderItems);
        orderItemsRecyclerView.setAdapter(adapter);

        // Thiết lập sự kiện click cho nút quay lại
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Thiết lập sự kiện click cho nút xóa
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trong thực tế, bạn sẽ xóa đơn hàng khỏi cơ sở dữ liệu
                Toast.makeText(OrderDetailActivity.this, "Đã xóa đơn hàng", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Thiết lập sự kiện click cho nút lưu
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trong thực tế, bạn sẽ cập nhật đơn hàng trong cơ sở dữ liệu
                Toast.makeText(OrderDetailActivity.this, "Đã lưu thay đổi", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private Order getSampleOrder(String orderId) {
        // Đây chỉ là dữ liệu mẫu, trong thực tế bạn sẽ truy vấn cơ sở dữ liệu
        if ("#DH212384".equals(orderId)) {
            return new Order("DH212384", 2580000, "22/05/2023", "Chưa xác nhận");
        }
        return new Order("DH212384", 2580000, "22/05/2023", "Chưa xác nhận");
    }

    private List<OrderItem> getSampleOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        
        // Thêm dữ liệu mẫu
        Product product1 = new Product("SP001", "Sữa Rửa Mặt CeraVe Foaming Facial Cleanser", 
                150000, 350000, 120, "Skincare", "");
        items.add(new OrderItem(product1, 4));
        
        Product product2 = new Product("SP004", "Kem Dưỡng Ẩm La Roche-Posay Cicaplast Baume B5", 
                220000, 450000, 275, "Skincare", "");
        items.add(new OrderItem(product2, 1));
        
        return items;
    }

    private void displayOrderDetails(Order order) {
        // Hiển thị thông tin đơn hàng
        orderIdTextView.setText("#" + order.getId());
        orderDateTextView.setText(order.getDate());
        customerNameTextView.setText("Phạm Nhật Vượng");
        customerPhoneTextView.setText("0931926260");
        itemCountTextView.setText("5 Sản phẩm");
        totalTextView.setText(formatPrice(order.getTotal()) + " đ");
        statusTextView.setText(order.getStatus());
        waitingTimeTextView.setText("2 ngày 4 giờ");
    }

    private String formatPrice(double price) {
        return String.format("%,.0f", price);
    }
}

