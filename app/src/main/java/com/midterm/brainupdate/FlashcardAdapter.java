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
    private OnItemClickListener listener;

    // Interface để xử lý sự kiện nhấn
    public interface OnItemClickListener {
        void onItemClick(Flashcard flashcard);
    }

    // Khởi tạo Adapter với danh sách flashcards
    public FlashcardAdapter(List<Flashcard> flashcardList) {
        this.flashcardList = flashcardList;
    }

    // Phương thức để đặt OnItemClickListener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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

        // Gọi phương thức bind để thiết lập sự kiện nhấn
        holder.bind(flashcard, listener);
    }

    @Override
    public int getItemCount() {
        return flashcardList.size();
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

        // Phương thức bind để gán sự kiện nhấn vào item
        public void bind(final Flashcard flashcard, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(flashcard);
                }
            });
        }
    }
}
