package com.project.myapplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.midterm.brainupdate.MyModel;
import com.midterm.brainupdate.R;

import java.util.ArrayList;
import java.util.List;

public class AddCard extends AppCompatActivity {

    private EditText thuat_ngu;
    private EditText dinh_nghia;
    private EditText title_input;
    private Button addButton;
    private ImageView back, complete;
    private LinearLayout container;
    private ScrollView scrollview;
    private List<MyModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addcard_container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        thuat_ngu = findViewById(R.id.thuat_ngu);
        dinh_nghia = findViewById(R.id.dinh_nghia);
        title_input = findViewById(R.id.title_input);
        addButton = findViewById(R.id.add);
        container = findViewById(R.id.container);
        scrollview = findViewById(R.id.scrollview);
        back = findViewById(R.id.back);
        complete = findViewById(R.id.complete);

        // Xử lý sự kiện nhấn nút "Add Text"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy nội dung từ hai EditText
                String text1 = thuat_ngu.getText().toString().trim();
                String text2 = dinh_nghia.getText().toString().trim();
                String text = "Thuật ngữ: " + text1 + "\nĐịnh nghĩa: " + text2;

                list.add(new MyModel(1, 1, text1, text2));

                // Kiểm tra nếu cả hai EditText đều không trống
                if (!text1.isEmpty() && !text2.isEmpty()) {
                    // Tạo một TextView mới
                    TextView textView = new TextView(AddCard.this);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    textView.setText(text);
                    textView.setTextSize(18);
                    textView.setPadding(8, 8, 8, 8);
                    textView.setTextColor(Color.BLACK);
                    textView.setTag(list.size());

                    // Đặt nền là drawable viền
                    textView.setBackgroundResource(R.drawable.textview_border);


                    // Thao tác xóa đi textview khi chạm vào
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Tạo một AlertDialog để hiển thị tùy chọn
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddCard.this);
                            builder.setTitle("Tùy chọn")
                                    .setMessage("Bạn có muốn xóa mục này không?")

                                    // Tùy chọn "Xóa"
                                    .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Xóa TextView khỏi container
                                            int i = (int) v.getTag();
                                            list.remove(i);
                                            container.removeView(textView);
                                            updateTextViewTags();
                                        }
                                    })

                                    // Tùy chọn "Hủy"
                                    .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Đóng dialog mà không làm gì
                                            dialog.dismiss();
                                        }
                                    });

                            // Hiển thị AlertDialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                    // Thêm TextView vào LinearLayout (container)
                    container.addView(textView);

                    // Xóa nội dung trong hai EditText sau khi thêm
                    thuat_ngu.setText("");
                    dinh_nghia.setText("");

                    // Cuộn xuống cuối ScrollView
                    scrollview.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollview.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
                else{
                    AlertDialog.Builder loi = new AlertDialog.Builder(AddCard.this);
                    loi.setTitle("Thông báo")
                            .setMessage("Hãy nhập đầy đủ thuật ngữ và định nghĩa!!!")
                            .setNegativeButton("Đã hiểu", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Đóng dialog mà không làm gì
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = loi.create();
                    dialog.show();
                }
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title_input.getText().toString().equals("")){
                    AlertDialog.Builder tb = new AlertDialog.Builder(AddCard.this);
                    tb.setTitle("Thông báo")
                            .setMessage("Hãy nhập tiêu đề!!!")
                            .setNegativeButton("Đã hiểu", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Đóng dialog mà không làm gì
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = tb.create();
                    dialog.show();
                }else if (list.isEmpty()){
                    AlertDialog.Builder tb = new AlertDialog.Builder(AddCard.this);
                    tb.setTitle("Thông báo")
                            .setMessage("Hãy tạo thẻ!!!")
                            .setNegativeButton("Đã hiểu", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Đóng dialog mà không làm gì
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = tb.create();
                    dialog.show();
                }else {
                    AlertDialog.Builder tb = new AlertDialog.Builder(AddCard.this);
                    tb.setTitle("Thông báo")
                            .setMessage("Đã tạo thành công!!!")
                            .setNegativeButton("Đã hiểu", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Đóng dialog mà không làm gì
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = tb.create();
                    dialog.show();
                    container.removeAllViews();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void updateTextViewTags() {
        for (int i = 0; i < container.getChildCount(); i++) {
            View view = container.getChildAt(i);
            view.setTag(i); // Cập nhật lại tag của mỗi TextView
        }
    }
}