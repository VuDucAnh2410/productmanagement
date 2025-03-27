package com.example.productmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.activities.customer.CustomerListActivity;
import com.example.productmanagement.adapters.ProductAdapter;
import com.example.productmanagement.models.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private FloatingActionButton fabAddProduct;
    private EditText searchEditText;
    private LinearLayout customerLayout, productLayout, cartLayout, orderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Khởi tạo các thành phần giao diện
        recyclerView = findViewById(R.id.product_recycler_view);
        fabAddProduct = findViewById(R.id.fab_add_product);
        searchEditText = findViewById(R.id.search_edit_text);

        customerLayout = findViewById(R.id.customer_layout);
        productLayout = findViewById(R.id.product_layout);
        cartLayout = findViewById(R.id.cart_layout);
        orderLayout = findViewById(R.id.order_layout);

        // Thiết lập RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo dữ liệu mẫu
        productList = createSampleProducts();

        // Thiết lập adapter
        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        // Thiết lập sự kiện click cho nút thêm sản phẩm
        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, AddProductActivity.class);
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
                filterProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Thiết lập sự kiện click cho các menu
        setupBottomNavigation();
    }

    private List<Product> createSampleProducts() {
        List<Product> products = new ArrayList<>();

        // Thêm dữ liệu mẫu
        products.add(new Product("SP001", "Sữa Rửa Mặt CeraVe Foaming Facial Cleanser",
                150000, 350000, 120, "Skincare",
                "Loại da phù hợp: Da dầu, da hỗn hợp\nThành phần chính: Niacinamide, Ceramides, Hyaluronic Acid\nCông dụng:\nLàm sạch sâu mà không làm khô da\nKiểm soát dầu thừa, giảm mụn\nBảo vệ hàng rào độ ẩm tự nhiên của da"));

        products.add(new Product("SP002", "Toner Klairs Supple Preparation Unscented Toner",
                180000, 380000, 480, "Skincare",
                "Loại da phù hợp: Mọi loại da, đặc biệt da nhạy cảm\nThành phần chính: Chiết xuất thực vật, Hyaluronic Acid\nCông dụng:\nCân bằng độ pH\nLàm dịu và cấp ẩm cho da\nKhông chứa hương liệu"));

        products.add(new Product("SP003", "Serum The Ordinary Niacinamide 10% + Zinc 1%",
                200000, 420000, 230, "Skincare",
                "Loại da phù hợp: Da dầu, da mụn\nThành phần chính: Niacinamide 10%, Zinc 1%\nCông dụng:\nGiảm dầu thừa và mụn\nLàm đều màu da\nGiảm lỗ chân lông to"));

        products.add(new Product("SP004", "Kem Dưỡng Ẩm La Roche-Posay Cicaplast Baume B5",
                220000, 450000, 275, "Skincare",
                "Loại da phù hợp: Da khô, da nhạy cảm, da bị tổn thương\nThành phần chính: Panthenol, Madecassoside\nCông dụng:\nLàm dịu da bị kích ứng\nPhục hồi da bị tổn thương\nCấp ẩm sâu"));

        products.add(new Product("SP005", "Kem Chống Nắng Anessa Perfect UV Sunscreen",
                300000, 650000, 150, "Skincare",
                "Loại da phù hợp: Mọi loại da\nThành phần chính: Các chất chống nắng phổ rộng\nCông dụng:\nBảo vệ da khỏi tia UVA/UVB\nChống nước, chống mồ hôi\nKhông gây nhờn rít"));

        return products;
    }

    private void setupBottomNavigation() {
        customerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CustomerListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đã ở màn hình Sản phẩm
            }
        });

        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        orderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void filterProducts(String query) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product : createSampleProducts()) {
            if (product.getName().toLowerCase().contains(query.toLowerCase()) ||
                    product.getCode().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }

        adapter = new ProductAdapter(this, filteredList);
        recyclerView.setAdapter(adapter);
    }
}

