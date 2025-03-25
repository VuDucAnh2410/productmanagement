package com.example.productmanagement.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.productmanagement.R;
import com.example.productmanagement.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productCodeTextView, productNameTextView, productCostTextView, 
                     productPriceTextView, productStockTextView, productDescriptionTextView;
    private Button editButton, deleteButton;

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

        // Lấy dữ liệu sản phẩm từ Intent
        if (getIntent().hasExtra("product_code")) {
            String productCode = getIntent().getStringExtra("product_code");
            
            // Trong thực tế, bạn sẽ truy vấn cơ sở dữ liệu để lấy thông tin sản phẩm
            // Đối với ứng dụng mẫu này, chúng ta sẽ tạo dữ liệu mẫu
            Product product = getSampleProduct(productCode);
            
            if (product != null) {
                displayProductDetails(product);
            }
        }

        // Thiết lập sự kiện click cho nút chỉnh sửa
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trong thực tế, bạn sẽ mở màn hình chỉnh sửa sản phẩm
                Toast.makeText(ProductDetailActivity.this, "Chức năng chỉnh sửa sản phẩm", Toast.LENGTH_SHORT).show();
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

    private Product getSampleProduct(String productCode) {
        // Đây chỉ là dữ liệu mẫu, trong thực tế bạn sẽ truy vấn cơ sở dữ liệu
        if ("SP001".equals(productCode)) {
            return new Product("SP001", "Sữa Rửa Mặt CeraVe Foaming Facial Cleanser", 
                    150000, 350000, 120, "Skincare", 
                    "Loại da phù hợp: Da dầu, da hỗn hợp\nThành phần chính: Niacinamide, Ceramides, Hyaluronic Acid\nCông dụng:\nLàm sạch sâu mà không làm khô da\nKiểm soát dầu thừa, giảm mụn\nBảo vệ hàng rào độ ẩm tự nhiên của da");
        }
        return null;
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

