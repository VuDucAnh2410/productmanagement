package com.example.productmanagement.activities.customer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.activities.CartActivity;
import com.example.productmanagement.activities.OrderActivity;
import com.example.productmanagement.activities.ProductActivity;
import com.example.productmanagement.adapters.CustomerAdapter;
import com.example.productmanagement.models.Customer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private RecyclerView customerRecyclerView;
    private CustomerAdapter adapter;
    private List<Customer> customerList;
    private EditText searchEditText;
    private TextView customerCountTextView;
    private FloatingActionButton fabAddCustomer;
    private LinearLayout customerLayout, productLayout, cartLayout, orderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_list);

        // Khởi tạo các thành phần giao diện
        customerRecyclerView = findViewById(R.id.customer_recycler_view);
        searchEditText = findViewById(R.id.search_edit_text);
        customerCountTextView = findViewById(R.id.customer_count_text_view);
        fabAddCustomer = findViewById(R.id.fab_add_customer);

        customerLayout = findViewById(R.id.customer_layout);
        productLayout = findViewById(R.id.product_layout);
        cartLayout = findViewById(R.id.cart_layout);
        orderLayout = findViewById(R.id.order_layout);

        // Thiết lập RecyclerView
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo dữ liệu mẫu
        customerList = createSampleCustomers();

        // Cập nhật số lượng khách hàng
        customerCountTextView.setText(customerList.size() + " khách hàng");

        // Thiết lập adapter
        adapter = new CustomerAdapter(this, customerList);
        customerRecyclerView.setAdapter(adapter);

        // Thiết lập sự kiện click cho nút thêm khách hàng
        fabAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình thêm khách hàng
                Intent intent = new Intent(CustomerListActivity.this, CustomerAddActivity.class);
                startActivity(intent);
            }
        });

        // Thiết lập sự kiện tìm kiếm
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCustomers(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Thiết lập sự kiện click cho các menu
        setupBottomNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cập nhật lại danh sách khi quay lại màn hình này
        customerList = createSampleCustomers();
        adapter = new CustomerAdapter(this, customerList);
        customerRecyclerView.setAdapter(adapter);
        customerCountTextView.setText(customerList.size() + " khách hàng");
    }

    private List<Customer> createSampleCustomers() {
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer("Long Liên", "0777123456", "longliên@gmail.com", "Nam", "22/05/1990", "Hà Nội"));
        customers.add(new Customer("Lương Văn", "01113145678", "luongvan@gmail.com", "Nam", "15/08/1985", "Hồ Chí Minh"));
        customers.add(new Customer("Lê Vương", "0905789456", "levuong@gmail.com", "Nam", "10/12/1992", "Đà Nẵng"));
        customers.add(new Customer("Lê Tuyết", "0905123456", "letuyet@gmail.com", "Nữ", "14/06/2000", "Đà Nẵng"));
        customers.add(new Customer("Phương Viên", "0111123456", "phuongvien@gmail.com", "Nữ", "25/03/1995", "Hải Phòng"));
        customers.add(new Customer("Lê Long", "0945678912", "lelong@gmail.com", "Nam", "30/11/1988", "Cần Thơ"));

        return customers;
    }

    private void filterCustomers(String query) {
        List<Customer> filteredList = new ArrayList<>();

        for (Customer customer : createSampleCustomers()) {
            if (customer.getName().toLowerCase().contains(query.toLowerCase()) ||
                    customer.getPhone().contains(query)) {
                filteredList.add(customer);
            }
        }

        adapter = new CustomerAdapter(this, filteredList);
        customerRecyclerView.setAdapter(adapter);
        customerCountTextView.setText(filteredList.size() + " khách hàng");
    }

    private void setupBottomNavigation() {
        customerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đã ở màn hình Khách hàng
            }
        });

        productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerListActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerListActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        orderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerListActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
