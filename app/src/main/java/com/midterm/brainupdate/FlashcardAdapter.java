package com.midterm.brainupdate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.ViewHolder> {
    private List<Flashcard> flashcardList;
    public FlashcardAdapter(List<Flashcard> flashcardList) {
        this.flashcardList = flashcardList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flashcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Gán dữ liệu cho các item (Flashcard)
        Flashcard flashcard = flashcardList.get(position);
        holder.textTitle.setText(flashcard.getTitle());
        holder.textFlashcards.setText(flashcard.getQuantity() + " Flashcards");
    }


    @Override
    public int getItemCount() {
        return flashcardList.size(); // Số lượng Flashcards
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textFlashcards;
        ImageView imageStar;

        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textFlashcards = itemView.findViewById(R.id.textFlashcards);
            imageStar = itemView.findViewById(R.id.imageStar);
        }
    }
}
