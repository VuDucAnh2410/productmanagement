package com.example.productmanagement.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
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

public class PaymentActivity extends AppCompatActivity {

    private TextView customerNameTextView, customerPhoneTextView, customerAddressTextView, totalTextView;
    private RecyclerView orderItemsRecyclerView;
    private Button printInvoiceButton;
    private ImageButton backButton;
    private EditText couponEditText;
    private Button applyCouponButton;
    private RadioButton cashRadioButton, bankTransferRadioButton;
    private OrderItemAdapter adapter;
    private List<OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Khởi tạo các thành phần giao diện
        customerNameTextView = findViewById(R.id.customer_name_text_view);
        customerPhoneTextView = findViewById(R.id.customer_phone_text_view);
        customerAddressTextView = findViewById(R.id.customer_address_text_view);
        totalTextView = findViewById(R.id.total_text_view);
        orderItemsRecyclerView = findViewById(R.id.order_items_recycler_view);
        printInvoiceButton = findViewById(R.id.print_invoice_button);
        backButton = findViewById(R.id.back_button);
        couponEditText = findViewById(R.id.coupon_edit_text);
        applyCouponButton = findViewById(R.id.apply_coupon_button);
        cashRadioButton = findViewById(R.id.cash_radio_button);
        bankTransferRadioButton = findViewById(R.id.bank_transfer_radio_button);

        // Thiết lập RecyclerView
        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Tạo dữ liệu mẫu
        orderItems = createSampleOrderItems();
        
        // Thiết lập adapter
        adapter = new OrderItemAdapter(this, orderItems);
        orderItemsRecyclerView.setAdapter(adapter);

        // Thiết lập dữ liệu khách hàng
        customerNameTextView.setText("Trần Văn Sướng");
        customerPhoneTextView.setText("0984357636");
        customerAddressTextView.setText("293/1 Tôn Đản quận 4");

        // Tính tổng tiền
        double total = calculateTotal();
        totalTextView.setText(String.format("%,.0f đ", total));

        // Thiết lập sự kiện click cho nút quay lại
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Thiết lập sự kiện click cho nút áp dụng mã khuyến mãi
        applyCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String couponCode = couponEditText.getText().toString().trim();
                if (!couponCode.isEmpty()) {
                    // Trong thực tế, bạn sẽ kiểm tra mã khuyến mãi trong cơ sở dữ liệu
                    Toast.makeText(PaymentActivity.this, "Đã áp dụng mã khuyến mãi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PaymentActivity.this, "Vui lòng nhập mã khuyến mãi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Thiết lập sự kiện click cho nút in hóa đơn
        printInvoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trong thực tế, bạn sẽ xử lý in hóa đơn và lưu đơn hàng
                Toast.makeText(PaymentActivity.this, "Đã in hóa đơn thanh toán", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private List<OrderItem> createSampleOrderItems() {
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

    private double calculateTotal() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getProduct().getSellingPrice() * item.getQuantity();
        }
        return total;
    }
}

