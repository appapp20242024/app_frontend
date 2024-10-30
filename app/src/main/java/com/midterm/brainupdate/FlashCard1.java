package com.midterm.brainupdate;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.animation.ObjectAnimator;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;


public class FlashCard1 extends AppCompatActivity {

    private TextView card;
    private TextView title, number;
    private boolean isBackVisible = false;
    private ImageView prev_card, next_card, back;
    private int current_card = 1, length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.flash_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.flashcard_container), (v, insets) -> {
            return insets.consumeSystemWindowInsets();
        });

        title = findViewById(R.id.title);
        card = findViewById(R.id.card);
        prev_card = findViewById(R.id.prev_card);
        next_card = findViewById(R.id.next_card);
        number = findViewById(R.id.number);
        back = findViewById(R.id.back);

//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//
//        // Gọi hàm getData() từ ApiService
//        Call<List<MyModel>> call = apiService.getData();
//        call.enqueue(new Callback<List<MyModel>>() {
//            @Override
//            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<MyModel> dataList = response.body();
//                    // Xử lý dữ liệu nhận được từ API
//                    for (MyModel item : dataList) {
//                        Log.d("API_DATA", "ID: " + item.getId() + ", Name: " + item.getName());
//                    }
//                } else {
//                    Toast.makeText(FlashCard.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<MyModel>> call, Throwable t) {
//                Toast.makeText(FlashCard.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        List<MyModel> list = new ArrayList<>();
        MyModel data = new MyModel(1, 1, "happy", "vui vẻ");
        list.add(data);
        list.add(new MyModel(1,1,"scared", "Sợ hãi"));
        list.add(new MyModel(1, 1, "angry", "Tức giận"));
        list.add(new MyModel(1,1, "sad","buồn"));
        length = list.size();

        card.setText(list.get(current_card-1).getQuestion());
        number.setText(current_card + "/" + length);

        prev_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_card>1){
                    current_card-=1;
                    number.setText(current_card + "/" + length);
                    card.setText(list.get(current_card-1).getQuestion());
                    isBackVisible = false;
                }
            }
        });

        next_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_card<length){
                    current_card++;
                    number.setText(current_card + "/" + length);
                    card.setText(list.get(current_card-1).getQuestion());
                    isBackVisible = false;
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        card.setOnClickListener(view -> flipTextView(list, current_card));
    }

    private void flipTextView(List<MyModel> list, int current_card) {
        // Xoay từ 0 đến 90 độ (ẩn mặt trước)
        ObjectAnimator frontAnimator = ObjectAnimator.ofFloat(card, "rotationY", 0f, 90f);
        frontAnimator.setDuration(300);

        // Đổi nội dung và tiếp tục xoay từ 90 đến 180 độ (hiện mặt sau)
        frontAnimator.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                // Đổi nội dung TextView khi xoay đến 90 độ
                if (!isBackVisible) {
                    card.setText(list.get(current_card-1).getAnswer());
                } else {
                    card.setText(list.get(current_card-1).getQuestion());
                }
                isBackVisible = !isBackVisible;

                // Xoay tiếp từ 90 đến 180 độ
                ObjectAnimator backAnimator = ObjectAnimator.ofFloat(card, "rotationY", -90f, 0f);
                backAnimator.setDuration(300);
                backAnimator.start();

            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(frontAnimator);
        animatorSet.start();
    }
}