package com.midterm.brainupdate;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFolders;
    private RecyclerView recyclerViewCourses;
    private FolderAdapter folderAdapter;
    private FlashcardAdapter courseAdapter;
    private RadioGroup radioGroup;
    private RadioButton radioFolders, radioCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        BottomNavigationView bottomNavigation = new BottomNavigationView(this, findViewById(R.id.bottom_navigation_container));

        recyclerViewFolders = findViewById(R.id.recyclerViewFolders);
        recyclerViewCourses = findViewById(R.id.recyclerViewCourses);
        radioGroup = findViewById(R.id.radioGroup);
        radioFolders = findViewById(R.id.radioFolders);
        radioCourses = findViewById(R.id.radioCourses);

        // Thiết lập layout manager
        recyclerViewFolders.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách folders
        List<Folder> folderList = new ArrayList<>();
        folderList.add(new Folder("Ó Văn Chánh"));
        folderList.add(new Folder("Tháng 10"));
        folderList.add(new Folder("Học kỳ II"));
        folderList.add(new Folder("Học kỳ I"));
        folderList.add(new Folder("Học kỳ hè"));
        folderList.add(new Folder("日本語"));
        folderList.add(new Folder("日本語　２"));

        // Tạo danh sách courses
        List<Flashcard> flashcardList = new ArrayList<>();
        flashcardList.add(new Flashcard("UI UX Design", 10));
        flashcardList.add(new Flashcard("Graphic Design", 20));
        flashcardList.add(new Flashcard("C＃", 20));
        flashcardList.add(new Flashcard("Tiếng Nghệ An", 20));
        flashcardList.add(new Flashcard("React-Native", 20));
        flashcardList.add(new Flashcard("Node JS", 20));
        flashcardList.add(new Flashcard("HTML - CSS", 20));
        flashcardList.add(new Flashcard("PHP", 20));

        folderAdapter = new FolderAdapter(folderList);
        courseAdapter = new FlashcardAdapter(flashcardList);

        recyclerViewFolders.setAdapter(folderAdapter);
        recyclerViewCourses.setAdapter(courseAdapter);

        // Lắng nghe sự thay đổi trong RadioGroup
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioFolders) {
                recyclerViewFolders.setVisibility(View.VISIBLE);
                recyclerViewCourses.setVisibility(View.GONE);
            } else if (checkedId == R.id.radioCourses) {
                recyclerViewFolders.setVisibility(View.GONE);
                recyclerViewCourses.setVisibility(View.VISIBLE);
            }
        });

        // Mặc định hiển thị danh sách học phần
        recyclerViewFolders.setVisibility(View.GONE);
        recyclerViewCourses.setVisibility(View.VISIBLE);
    }
}