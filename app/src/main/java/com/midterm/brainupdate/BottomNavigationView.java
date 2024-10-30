package com.midterm.brainupdate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BottomNavigationView {

    private LinearLayout bottomNavigationLayout;
    public static final int CREATE_COURSE_REQUEST_CODE = 1;
    public BottomNavigationView(Context context, ViewGroup parentLayout) {
        // Inflate layout từ file XML
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bottomNavigationLayout = (LinearLayout) inflater.inflate(R.layout.bottom_navigation, parentLayout, false);
        parentLayout.addView(bottomNavigationLayout); // Thêm vào layout cha

        // Thiết lập sự kiện cho các icon
        setupClickListeners(context);
    }


    private void setupClickListeners(Context context) {
        ImageView homeIcon = bottomNavigationLayout.findViewById(R.id.homeIcon);
        ImageView libraryIcon = bottomNavigationLayout.findViewById(R.id.libraryIcon);
        ImageView addIcon = bottomNavigationLayout.findViewById(R.id.addIcon);
        ImageView noteIcon = bottomNavigationLayout.findViewById(R.id.noteIcon);
        ImageView profileIcon = bottomNavigationLayout.findViewById(R.id.profileIcon);

        homeIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        });

        libraryIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, LibraryActivity.class);
            context.startActivity(intent);
        });

        addIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, CreatCourseActivity.class);
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, CREATE_COURSE_REQUEST_CODE); // Thay đổi ở đây
            }
        });


        noteIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, NoteActivity.class);
            context.startActivity(intent);
        });

        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            context.startActivity(intent);
        });
    }
}
