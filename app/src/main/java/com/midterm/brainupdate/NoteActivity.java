package com.midterm.brainupdate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.note_container), (v, insets) -> {
            return insets.consumeSystemWindowInsets();
        });

        BottomNavigationView bottomNavigation = new BottomNavigationView(this, findViewById(R.id.bottom_navigation_container));

        RecyclerView recyclerView = findViewById(R.id.recentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách flashcards mẫu
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("Note 1", 33));
        flashcards.add(new Flashcard("Note 2", 11));
        flashcards.add(new Flashcard("Note 3", 22));
        flashcards.add(new Flashcard("Note 4", 01));

        // Khởi tạo adapter và gán cho RecyclerView
        FlashcardAdapter adapter = new FlashcardAdapter(flashcards);
        recyclerView.setAdapter(adapter);
        // Thiết lập sự kiện nhấn cho các mục trong RecyclerView
        adapter.setOnItemClickListener(note -> {
            Toast.makeText(NoteActivity.this, "Đã nhấn vào: " + note.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NoteActivity.this, FlashCard1.class);

            startActivity(intent);
        });
    }
}