package com.midterm.brainupdate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {
    private List<Folder> folderList;

    public FolderAdapter(List<Folder> folderList) {
        this.folderList = folderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_folder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Gán dữ liệu cho các item (Folder)
        holder.textFolderTitle.setText(folderList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return folderList.size(); // Số lượng folders
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textFolderTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            textFolderTitle = itemView.findViewById(R.id.textFolderTitle);
        }
    }
}
