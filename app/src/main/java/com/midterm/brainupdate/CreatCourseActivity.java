package com.midterm.brainupdate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreatCourseActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextTerm1, editTextDefinition1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_creat_course);

        // Ánh xạ các EditText
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextTerm1 = findViewById(R.id.editTextTerm1);
        editTextDefinition1 = findViewById(R.id.editTextDefinition1);


        // Ánh xạ nút "Tạo học phần"
        Button buttonCreateCourse = findViewById(R.id.buttonCreateCourse);
        buttonCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String title = editTextTitle.getText().toString();
                String term1 = editTextTerm1.getText().toString();
                String definition1 = editTextDefinition1.getText().toString();

                // Tạo Intent để gửi dữ liệu về LibraryActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("flashcard_title", title);
                // Nếu cần thêm các thông tin khác, bạn có thể thêm vào đây
                setResult(RESULT_OK, resultIntent);
                finish(); // Kết thúc Activity và quay về LibraryActivity
            }
        });


    }
}