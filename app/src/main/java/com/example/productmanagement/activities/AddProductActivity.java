package com.example.productmanagement.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.productmanagement.R;

public class AddProductActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView productImageView;
    private EditText productCodeEditText, productNameEditText, productCostEditText, 
                     productPriceEditText, productStatusEditText, productDescriptionEditText;
    private Button cancelButton, confirmButton;
    private TextView uploadImageTextView;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Khởi tạo các thành phần giao diện
        productImageView = findViewById(R.id.product_image_view);
        productCodeEditText = findViewById(R.id.product_code_edit_text);
        productNameEditText = findViewById(R.id.product_name_edit_text);
        productCostEditText = findViewById(R.id.product_cost_edit_text);
        productPriceEditText = findViewById(R.id.product_price_edit_text);
        productStatusEditText = findViewById(R.id.product_status_edit_text);
        productDescriptionEditText = findViewById(R.id.product_description_edit_text);
        cancelButton = findViewById(R.id.cancel_button);
        confirmButton = findViewById(R.id.confirm_button);
        uploadImageTextView = findViewById(R.id.upload_image_text_view);

        // Thiết lập sự kiện click cho nút tải ảnh lên
        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        uploadImageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        // Thiết lập sự kiện click cho nút hủy
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Thiết lập sự kiện click cho nút xác nhận
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            productImageView.setImageURI(imageUri);
            uploadImageTextView.setVisibility(View.GONE);
        }
    }

    private void saveProduct() {
        String code = productCodeEditText.getText().toString().trim();
        String name = productNameEditText.getText().toString().trim();
        String costStr = productCostEditText.getText().toString().trim();
        String priceStr = productPriceEditText.getText().toString().trim();
        String status = productStatusEditText.getText().toString().trim();
        String description = productDescriptionEditText.getText().toString().trim();

        // Kiểm tra dữ liệu nhập vào
        if (code.isEmpty() || name.isEmpty() || costStr.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chuyển đổi giá thành số
        double cost, price;
        try {
            cost = Double.parseDouble(costStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Trong thực tế, bạn sẽ lưu sản phẩm vào cơ sở dữ liệu ở đây
        // Đối với ứng dụng mẫu này, chúng ta chỉ hiển thị thông báo thành công
        Toast.makeText(this, "Đã thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
        finish();
    }
}

