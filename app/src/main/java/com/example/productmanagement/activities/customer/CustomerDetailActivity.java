package com.example.productmanagement.activities.customer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.productmanagement.R;
import com.example.productmanagement.activities.CartActivity;
import com.example.productmanagement.activities.OrderActivity;
import com.example.productmanagement.activities.ProductActivity;
import com.example.productmanagement.models.Customer;
import com.google.android.material.button.MaterialButton;

public class CustomerDetailActivity extends AppCompatActivity {

    private ImageButton backButton, editButton;
    private TextView customerNameTextView, customerBirthdayTextView, customerAddressTextView;
    private TextView customerNoteTextView, customerPhoneTextView, customerEmailTextView;
    private TextView customerAddressNameTextView, customerAddressPhoneTextView, customerFullAddressTextView;
    private TextView customerTagsTextView;
    private ImageButton callButton, smsButton, zaloButton, emailButton;
    private MaterialButton defaultAddressButton;
    private LinearLayout customerLayout, productLayout, cartLayout, orderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_detail);

        // Khởi tạo các thành phần giao diện
        backButton = findViewById(R.id.back_button);
        editButton = findViewById(R.id.edit_button);
        customerNameTextView = findViewById(R.id.customer_name_text_view);
        customerBirthdayTextView = findViewById(R.id.customer_birthday_text_view);
        customerAddressTextView = findViewById(R.id.customer_address_text_view);
        customerNoteTextView = findViewById(R.id.customer_note_text_view);
        customerPhoneTextView = findViewById(R.id.customer_phone_text_view);
        customerEmailTextView = findViewById(R.id.customer_email_text_view);
        customerAddressNameTextView = findViewById(R.id.customer_address_name_text_view);
        customerAddressPhoneTextView = findViewById(R.id.customer_address_phone_text_view);
        customerFullAddressTextView = findViewById(R.id.customer_full_address_text_view);
        customerTagsTextView = findViewById(R.id.customer_tags_text_view);
        callButton = findViewById(R.id.call_button);
        smsButton = findViewById(R.id.sms_button);
        zaloButton = findViewById(R.id.zalo_button);
        emailButton = findViewById(R.id.email_button);
        defaultAddressButton = findViewById(R.id.default_address_button);

        // Khởi tạo bottom navigation
        customerLayout = findViewById(R.id.customer_layout);
        productLayout = findViewById(R.id.product_layout);
        cartLayout = findViewById(R.id.cart_layout);
        orderLayout = findViewById(R.id.order_layout);

        // Lấy dữ liệu khách hàng từ Intent
        if (getIntent().hasExtra("customer_name") && getIntent().hasExtra("customer_phone")) {
            String name = getIntent().getStringExtra("customer_name");
            String phone = getIntent().getStringExtra("customer_phone");
            String email = getIntent().getStringExtra("customer_email");
            String gender = getIntent().getStringExtra("customer_gender");
            String birthday = getIntent().getStringExtra("customer_birthday");
            String address = getIntent().getStringExtra("customer_address");

            // Hiển thị thông tin khách hàng
            customerNameTextView.setText(name);
            customerBirthdayTextView.setText(birthday);
            customerAddressTextView.setText(address);
            customerNoteTextView.setText("Ccp");
            customerPhoneTextView.setText(phone);
            customerEmailTextView.setText(email);
            customerAddressNameTextView.setText(name);
            customerAddressPhoneTextView.setText(phone);
            customerFullAddressTextView.setText("Cc, " + address);
            customerTagsTextView.setText("Chưa có tag nào");
        }

        // Thiết lập sự kiện click cho nút quay lại
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Thiết lập sự kiện click cho nút chỉnh sửa
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerDetailActivity.this, CustomerAddActivity.class);
                intent.putExtra("edit_mode", true);
                intent.putExtra("customer_id", customerPhoneTextView.getText().toString());
                intent.putExtra("customer_name", customerNameTextView.getText().toString());
                intent.putExtra("customer_phone", customerPhoneTextView.getText().toString());
                intent.putExtra("customer_email", customerEmailTextView.getText().toString());
                intent.putExtra("customer_gender", "");
                intent.putExtra("customer_birthday", customerBirthdayTextView.getText().toString());
                intent.putExtra("customer_address", customerAddressTextView.getText().toString());
                startActivity(intent);
            }
        });

        // Thiết lập sự kiện click cho các nút liên hệ
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = customerPhoneTextView.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = customerPhoneTextView.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + phone));
                startActivity(intent);
            }
        });

        zaloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerDetailActivity.this, "Mở Zalo", Toast.LENGTH_SHORT).show();
                // Trong thực tế, bạn sẽ mở ứng dụng Zalo hoặc trang web Zalo
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = customerEmailTextView.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email));
                startActivity(intent);
            }
        });

        // Thiết lập sự kiện click cho nút địa chỉ mặc định
        defaultAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerDetailActivity.this, "Đã đặt làm địa chỉ mặc định", Toast.LENGTH_SHORT).show();
            }
        });

        // Thiết lập sự kiện click cho các menu
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        customerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerDetailActivity.this, CustomerListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerDetailActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerDetailActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        orderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerDetailActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
