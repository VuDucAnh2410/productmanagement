package com.example.productmanagement.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.adapters.OrderItemAdapter;
import com.example.productmanagement.models.OrderItem;
import com.example.productmanagement.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderActivity extends AppCompatActivity {

    private EditText customerNameEditText, customerPhoneEditText, customerEmailEditText, customerAddressEditText;
    private RecyclerView orderItemsRecyclerView;
    private Button addButton, addNewItemButton;
    private TextView subtotalTextView, discountTextView;
    private ImageButton backButton;
    private OrderItemAdapter adapter;
    private List<OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        // Khởi tạo các thành phần giao diện
        customerNameEditText = findViewById(R.id.customer_name_edit_text);
        customerPhoneEditText = findViewById(R.id.customer_phone_edit_text);
        customerEmailEditText = findViewById(R.id.customer_email_edit_text);
        customerAddressEditText = findViewById(R.id.customer_address_edit_text);
        orderItemsRecyclerView = findViewById(R.id.order_items_recycler_view);
        addButton = findViewById(R.id.add_button);
        addNewItemButton = findViewById(R.id.add_new_item_button);
        subtotalTextView = findViewById(R.id.subtotal_text_view);
        discountTextView = findViewById(R.id.discount_text_view);
        backButton = findViewById(R.id.back_button); // Nếu ID này tồn tại trong XML

        // Thiết lập RecyclerView
        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo dữ liệu mẫu
        orderItems = createSampleOrderItems();

        // Thiết lập adapter
        adapter = new OrderItemAdapter(this, orderItems);
        orderItemsRecyclerView.setAdapter(adapter);

        // Thiết lập sự kiện click cho nút quay lại
        backButton.setOnClickListener(v -> finish());

        // Thiết lập sự kiện click cho nút thêm
        addButton.setOnClickListener(v -> saveOrder());

        // Thiết lập sự kiện click cho nút thêm sản phẩm mới
        addNewItemButton.setOnClickListener(v -> {
            if (!orderItems.isEmpty()) {
                // Lấy sản phẩm đầu tiên làm mẫu
                Product sampleProduct = orderItems.get(0).getProduct();
                // Tạo một sản phẩm mới với thông tin tương tự
                Product newProduct = new Product(
                        sampleProduct.getCode(),
                        sampleProduct.getName(),
                        sampleProduct.getCostPrice(),
                        sampleProduct.getSellingPrice(),
                        sampleProduct.getStock(),
                        sampleProduct.getCategory(),
                        sampleProduct.getDescription()
                );
                // Thêm vào danh sách với số lượng là 1
                orderItems.add(new OrderItem(newProduct, 1));
                // Cập nhật adapter
                adapter.notifyDataSetChanged();
                // Cập nhật tổng tiền
                updateTotal();
                // Thông báo
                Toast.makeText(CreateOrderActivity.this, "Đã thêm sản phẩm mới", Toast.LENGTH_SHORT).show();
            }
        });

        // Cập nhật tổng tiền
        updateTotal();
    }

    private List<OrderItem> createSampleOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        // Thêm dữ liệu mẫu
        Product product1 = new Product("SP001", "Sữa Rửa Mặt CeraVe Foaming Facial Cleanser",
                150000, 350000, 120, "Skincare", "");
        items.add(new OrderItem(product1, 1));
        return items;
    }

    private void updateTotal() {
        double subtotal = 0;
        for (OrderItem item : orderItems) {
            subtotal += item.getProduct().getSellingPrice() * item.getQuantity();
        }

        double discount = 0; // Có thể tính chiết khấu tùy theo quy tắc kinh doanh
        double total = subtotal - discount;

        subtotalTextView.setText(String.format("%,.0f đ", subtotal));
        discountTextView.setText(String.format("%,.0f đ", discount));
        // Cập nhật tổng tiền nếu cần thiết
        // totalTextView.setText(String.format("%,.0f đ", total)); // Nếu bạn có trường tổng tiền
    }

    private void saveOrder() {
        String name = customerNameEditText.getText().toString().trim();
        String phone = customerPhoneEditText.getText().toString().trim();
        String email = customerEmailEditText.getText().toString().trim();
        String address = customerAddressEditText.getText().toString().trim();

        // Kiểm tra dữ liệu nhập vào
        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin khách hàng", Toast.LENGTH_SHORT).show();
            return;
        }

        if (orderItems.isEmpty()) {
            Toast.makeText(this, "Vui lòng thêm sản phẩm vào đơn hàng", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thông báo thành công
        Toast.makeText(this, "Đã tạo đơn hàng thành công", Toast.LENGTH_SHORT).show();
        finish();
    }
}