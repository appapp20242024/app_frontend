package com.midterm.brainupdate;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home_container), (v, insets) -> {
            return insets.consumeSystemWindowInsets();
        });

        BottomNavigationView bottomNavigation = new BottomNavigationView(this, findViewById(R.id.bottom_navigation_container));

        RecyclerView recyclerView = findViewById(R.id.recentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách flashcards mẫu
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("C, C++ ", 10));
        flashcards.add(new Flashcard("Java Swing", 15));
        flashcards.add(new Flashcard("Node JS", 5));
        flashcards.add(new Flashcard("Flutter", 20));

        // Khởi tạo adapter và gán cho RecyclerView
        FlashcardAdapter adapter = new FlashcardAdapter(flashcards);
        recyclerView.setAdapter(adapter);
    }
}