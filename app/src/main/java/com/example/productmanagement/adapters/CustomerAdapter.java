package com.example.productmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productmanagement.R;
import com.example.productmanagement.activities.customer.CustomerAddActivity;
import com.example.productmanagement.activities.customer.CustomerDetailActivity;
import com.example.productmanagement.models.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private Context context;
    private List<Customer> customerList;

    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_list_item, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        final Customer customer = customerList.get(position);

        holder.customerNameTextView.setText(customer.getName());
        holder.customerPhoneTextView.setText(customer.getPhone());
        holder.customerOrderCountTextView.setText("0 đơn");
        holder.customerLastOrderTextView.setText("0 đ");

        // Thiết lập sự kiện click cho tên khách hàng
        holder.customerNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerDetail(customer);
            }
        });

        // Thiết lập sự kiện click cho toàn bộ item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerDetail(customer);
            }
        });

        // Thiết lập sự kiện click cho nút xóa
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trong thực tế, bạn sẽ xóa khách hàng khỏi cơ sở dữ liệu
                customerList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Đã xóa khách hàng", Toast.LENGTH_SHORT).show();
            }
        });

        // Thiết lập sự kiện click cho nút chỉnh sửa
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerAddActivity.class);
                intent.putExtra("edit_mode", true);
                intent.putExtra("customer_id", customer.getPhone());
                intent.putExtra("customer_name", customer.getName());
                intent.putExtra("customer_phone", customer.getPhone());
                intent.putExtra("customer_email", customer.getEmail());
                intent.putExtra("customer_gender", customer.getGender());
                intent.putExtra("customer_birthday", customer.getBirthday());
                intent.putExtra("customer_address", customer.getAddress());
                context.startActivity(intent);
            }
        });
    }

    private void openCustomerDetail(Customer customer) {
        Intent intent = new Intent(context, CustomerDetailActivity.class);
        intent.putExtra("customer_id", customer.getPhone());
        intent.putExtra("customer_name", customer.getName());
        intent.putExtra("customer_phone", customer.getPhone());
        intent.putExtra("customer_email", customer.getEmail());
        intent.putExtra("customer_gender", customer.getGender());
        intent.putExtra("customer_birthday", customer.getBirthday());
        intent.putExtra("customer_address", customer.getAddress());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        ImageView customerAvatar;
        View customerStatusIndicator;
        TextView customerNameTextView, customerPhoneTextView;
        TextView customerOrderCountTextView, customerLastOrderTextView;
        ImageButton deleteButton, editButton;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            customerAvatar = itemView.findViewById(R.id.customer_avatar);
            customerStatusIndicator = itemView.findViewById(R.id.customer_status_indicator);
            customerNameTextView = itemView.findViewById(R.id.customer_name_text_view);
            customerPhoneTextView = itemView.findViewById(R.id.customer_phone_text_view);
            customerOrderCountTextView = itemView.findViewById(R.id.customer_order_count_text_view);
            customerLastOrderTextView = itemView.findViewById(R.id.customer_last_order_text_view);
            deleteButton = itemView.findViewById(R.id.delete_button);
            editButton = itemView.findViewById(R.id.edit_button);
        }
    }
}
