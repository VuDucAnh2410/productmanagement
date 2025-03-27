package com.example.productmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.productmanagement.R;
import com.example.productmanagement.models.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productCodeTextView, productNameTextView, productCostTextView,
            productPriceTextView, productStockTextView, productDescriptionTextView;
    private Button editButton, deleteButton, backButton;
    private Map<String, Product> productDatabase = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Khởi tạo các thành phần giao diện
        productImageView = findViewById(R.id.product_image_view);
        productCodeTextView = findViewById(R.id.product_code_text_view);
        productNameTextView = findViewById(R.id.product_name_text_view);
        productCostTextView = findViewById(R.id.product_cost_text_view);
        productPriceTextView = findViewById(R.id.product_price_text_view);
        productStockTextView = findViewById(R.id.product_stock_text_view);
        productDescriptionTextView = findViewById(R.id.product_description_text_view);
        editButton = findViewById(R.id.edit_button);
        deleteButton = findViewById(R.id.delete_button);
        backButton = findViewById(R.id.back_button);

        // Lấy tiêu đề từ layout
        TextView titleTextView = findViewById(R.id.title_text_view);

        // Khởi tạo dữ liệu sản phẩm mẫu
        initializeProductDatabase();

        // Lấy dữ liệu sản phẩm từ Intent
        if (getIntent().hasExtra("product_code")) {
            String productCode = getIntent().getStringExtra("product_code");
            String productName = getIntent().getStringExtra("product_name");

            // Cập nhật tiêu đề nếu có tên sản phẩm
            if (productName != null && titleTextView != null) {
                titleTextView.setText(productName);
            }

            // Lấy thông tin sản phẩm từ database
            Product product = productDatabase.get(productCode);

            if (product != null) {
                displayProductDetails(product);
            }
        }

        // Thiết lập sự kiện click cho nút quay lại
        if (backButton != null) {
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        // Thiết lập sự kiện click cho nút chỉnh sửa
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, AddProductActivity.class);
                intent.putExtra("product_code", productCodeTextView.getText().toString());
                intent.putExtra("edit_mode", true);
                startActivity(intent);
            }
        });

        // Thiết lập sự kiện click cho nút xóa
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trong thực tế, bạn sẽ xóa sản phẩm khỏi cơ sở dữ liệu
                Toast.makeText(ProductDetailActivity.this, "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initializeProductDatabase() {
        // Thêm dữ liệu mẫu vào database
        productDatabase.put("SP001", new Product("SP001", "Sữa Rửa Mặt CeraVe Foaming Facial Cleanser",
                150000, 350000, 120, "Skincare",
                "Loại da phù hợp: Da dầu, da hỗn hợp\nThành phần chính: Niacinamide, Ceramides, Hyaluronic Acid\nCông dụng:\nLàm sạch sâu mà không làm khô da\nKiểm soát dầu thừa, giảm mụn\nBảo vệ hàng rào độ ẩm tự nhiên của da"));

        productDatabase.put("SP002", new Product("SP002", "Toner Klairs Supple Preparation Unscented Toner",
                180000, 380000, 480, "Skincare",
                "Loại da phù hợp: Mọi loại da, đặc biệt da nhạy cảm\nThành phần chính: Chiết xuất thực vật, Hyaluronic Acid\nCông dụng:\nCân bằng độ pH\nLàm dịu và cấp ẩm cho da\nKhông chứa hương liệu"));

        productDatabase.put("SP003", new Product("SP003", "Serum The Ordinary Niacinamide 10% + Zinc 1%",
                200000, 420000, 230, "Skincare",
                "Loại da phù hợp: Da dầu, da mụn\nThành phần chính: Niacinamide 10%, Zinc 1%\nCông dụng:\nGiảm dầu thừa và mụn\nLàm đều màu da\nGiảm lỗ chân lông to"));

        productDatabase.put("SP004", new Product("SP004", "Kem Dưỡng Ẩm La Roche-Posay Cicaplast Baume B5",
                220000, 450000, 275, "Skincare",
                "Loại da phù hợp: Da khô, da nhạy cảm, da bị tổn thương\nThành phần chính: Panthenol, Madecassoside\nCông dụng:\nLàm dịu da bị kích ứng\nPhục hồi da bị tổn thương\nCấp ẩm sâu"));

        productDatabase.put("SP005", new Product("SP005", "Kem Chống Nắng Anessa Perfect UV Sunscreen",
                300000, 650000, 150, "Skincare",
                "Loại da phù hợp: Mọi loại da\nThành phần chính: Các chất chống nắng phổ rộng\nCông dụng:\nBảo vệ da khỏi tia UVA/UVB\nChống nước, chống mồ hôi\nKhông gây nhờn rít"));
    }

    private void displayProductDetails(Product product) {
        // Hiển thị thông tin sản phẩm
        productCodeTextView.setText(product.getCode());
        productNameTextView.setText(product.getName());
        productCostTextView.setText(formatPrice(product.getCostPrice()) + " đ");
        productPriceTextView.setText(formatPrice(product.getSellingPrice()) + " đ");
        productStockTextView.setText("Còn hàng (" + product.getStock() + " Sản phẩm)");
        productDescriptionTextView.setText(product.getDescription());

        // Trong thực tế, bạn sẽ tải ảnh sản phẩm từ URL hoặc tài nguyên
        productImageView.setImageResource(R.drawable.product_placeholder);
    }

    private String formatPrice(double price) {
        return String.format("%,.0f", price);
    }
}

