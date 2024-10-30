package com.midterm.brainupdate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> noteList;

    // Constructor
    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flashcard, parent, false); // Đảm bảo layout item_note.xml tồn tại
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Gán dữ liệu cho các item (Note)
        Note note = noteList.get(position);
        holder.textTitle.setText(note.getTitle());

    }

    @Override
    public int getItemCount() {
        return noteList.size(); // Số lượng Notes
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textContent; // Đổi textFlashcards thành textContent
        ImageView imageStar;

        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            imageStar = itemView.findViewById(R.id.imageStar);
        }
    }
}
