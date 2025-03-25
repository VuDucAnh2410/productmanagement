package com.example.productmanagement.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.adapters.OrderAdapter;
import com.example.productmanagement.models.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    private EditText searchEditText, fromDateEditText, toDateEditText;
    private RecyclerView orderRecyclerView;
    private Button createOrderButton;
    private OrderAdapter adapter;
    private List<Order> orderList;
    private Calendar calendar;
    private LinearLayout customerLayout, productLayout, cartLayout, orderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Khởi tạo các thành phần giao diện
        searchEditText = findViewById(R.id.search_edit_text);
        fromDateEditText = findViewById(R.id.from_date_edit_text);
        toDateEditText = findViewById(R.id.to_date_edit_text);
        orderRecyclerView = findViewById(R.id.order_recycler_view);
        createOrderButton = findViewById(R.id.create_order_button);
        
        customerLayout = findViewById(R.id.customer_layout);
        productLayout = findViewById(R.id.product_layout);
        cartLayout = findViewById(R.id.cart_layout);
        orderLayout = findViewById(R.id.order_layout);

        // Khởi tạo Calendar
        calendar = Calendar.getInstance();

        // Thiết lập RecyclerView
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Tạo dữ liệu mẫu
        orderList = createSampleOrders();
        
        // Thiết lập adapter
        adapter = new OrderAdapter(this, orderList);
        orderRecyclerView.setAdapter(adapter);

        // Thiết lập sự kiện click cho các trường ngày tháng
        setupDatePickers();

        // Thiết lập sự kiện click cho nút tạo đơn hàng
        createOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, CreateOrderActivity.class);
                startActivity(intent);
            }
        });

        // Thiết lập sự kiện click cho các menu
        setupBottomNavigation();
    }

    private List<Order> createSampleOrders() {
        List<Order> orders = new ArrayList<>();
        
        // Thêm dữ liệu mẫu
        orders.add(new Order("DH212384", 999999999, "22/05/23", "Chưa xác nhận"));
        orders.add(new Order("DH212385", 888888888, "22/05/23", "Xác nhận"));
        orders.add(new Order("DH212386", 777777777, "22/05/23", "Xác nhận"));
        orders.add(new Order("DH212387", 666666666, "22/05/23", "Chưa xác nhận"));
        orders.add(new Order("DH212388", 555555555, "22/05/23", "Đang giao"));
        orders.add(new Order("DH212389", 444444444, "22/05/23", "Đang giao"));
        orders.add(new Order("DH212390", 333333333, "22/05/23", "Chưa xác nhận"));
        orders.add(new Order("DH212391", 222222222, "22/05/23", "Hủy đơn"));
        orders.add(new Order("DH212392", 111111111, "22/05/23", "Hủy đơn"));
        
        return orders;
    }

    private void setupDatePickers() {
        // Định dạng ngày tháng
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        
        // Thiết lập giá trị mặc định cho các trường ngày tháng
        fromDateEditText.setText(dateFormat.format(calendar.getTime()));
        toDateEditText.setText(dateFormat.format(calendar.getTime()));
        
        // Thiết lập sự kiện click cho trường ngày bắt đầu
        fromDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(fromDateEditText);
            }
        });
        
        // Thiết lập sự kiện click cho trường ngày kết thúc
        toDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(toDateEditText);
            }
        });
    }

    private void showDatePickerDialog(final EditText dateEditText) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                dateEditText.setText(dateFormat.format(calendar.getTime()));
            }
        };
        
        new DatePickerDialog(
                OrderActivity.this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
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
                Intent intent = new Intent(OrderActivity.this, ProductActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        orderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đã ở màn hình Đơn hàng
            }
        });
    }
}

