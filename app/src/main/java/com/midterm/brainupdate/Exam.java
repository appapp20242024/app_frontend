package com.midterm.brainupdate;

import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exam extends AppCompatActivity {

    private LinearLayout questionContainer;
    private int currentQuestion = 0;
    private int questionCount;
    private List<Exam_Question> list = new ArrayList<>();
    private List<RadioButton> questionRadioButton = new ArrayList<>();
    private TextView questionText;
    private Button submit;
    private ImageView prev_question, next_question, back;
    private RadioGroup radioGroup;
    private List<Integer> userAnswers = new ArrayList<>();
    private List<Integer> correctAnswers = new ArrayList<>();
    private List<Button> questionButton = new ArrayList<>();
    private Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exam);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questionContainer = findViewById(R.id.questionContainer);
        questionText = findViewById(R.id.questionText);
        prev_question = findViewById(R.id.prev_question);
        next_question = findViewById(R.id.next_question);
        submit = findViewById(R.id.submitButton);
        radioGroup = findViewById(R.id.radioGroup);
        back = findViewById(R.id.back);

        // Số lượng câu hỏi
        list.add(new Exam_Question("What is the largest ocean on Earth?", new ArrayList<>(Arrays.asList("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean")), "Pacific Ocean"));
        list.add(new Exam_Question("What is the capital of France?",new ArrayList<>(Arrays.asList("Berlin", "Madrid", "Paris", "Rome")),"Paris"));
        list.add(new Exam_Question("Which planet is known as the Red Planet?", new ArrayList<>(Arrays.asList("Earth", "Mars", "Jupiter", "Saturn")), "Mars"));

        questionCount = list.size();

        for (int i = 0; i < questionCount; i++) {
            userAnswers.add(-1);
            correctAnswers.add(list.get(i).getOption().indexOf(list.get(i).getAnswer())+1);
        }

        createRadioButtons();

        create_button();

        next_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQuestion<questionCount-1){
                    saveAnswer();
                    currentQuestion++;
                    updateQuestionButtons(questionContainer);
                    updateQuestion();
                }
            }
        });

        prev_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentQuestion>0){
                    saveAnswer();
                    currentQuestion--;
                    updateQuestionButtons(questionContainer);
                    updateQuestion();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = true;
                saveAnswer();
                updateQuestionButtons(questionContainer);
                updateQuestion();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateQuestionButtons(LinearLayout container) {
        for (int i = 0; i < container.getChildCount(); i++) {
            Button button = (Button) container.getChildAt(i);
            int questionIndex = Integer.parseInt(button.getText().toString()) - 1; // Chỉ số câu hỏi từ text của Button

            if (check) { // Nếu đã bấm nút submit
                // Kiểm tra câu trả lời của người dùng
                if (userAnswers.get(questionIndex) != null) { // Kiểm tra nếu câu hỏi đã được trả lời
                    // Nếu đúng, tô màu xanh
                    if (userAnswers.get(questionIndex).equals(correctAnswers.get(questionIndex))) {
                        button.setBackgroundTintList(getResources().getColorStateList(R.color.correctAnswer));
                    } else {
                        // Nếu sai, tô màu đỏ
                        button.setBackgroundTintList(getResources().getColorStateList(R.color.wrongAnswer));
                    }
                } else {
                    // Nếu chưa trả lời, giữ nguyên màu mặc định
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.defaultColor));
                }
            } else {
                // Nếu chưa bấm submit, tô màu mặc định hoặc màu hiện tại
                if (questionIndex == currentQuestion) {
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.selectedColor));
                } else {
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.defaultColor));
                }
            }
        }
    }



    private void create_button(){
        for (int i = 1; i <= questionCount; i++) {
            Button qb = new Button(this);
            questionButton.add(qb);
            questionButton.get(i-1).setText(String.valueOf(i));
            questionButton.get(i-1).setTextColor(Color.BLACK);
            questionButton.get(i-1).setBackgroundResource(R.drawable.circle_background); // Tạo background cho nút
            questionButton.get(i-1).setPadding(0, 0, 0, 0);

            // Thiết lập khoảng cách giữa các nút
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0); // Khoảng cách giữa các nút
            questionButton.get(i-1).setLayoutParams(params);

            // Đổi màu cho câu hỏi hiện tại
            if (i == currentQuestion+1) {
                questionButton.get(i-1).setBackgroundTintList(getResources().getColorStateList(R.color.selectedColor));
            } else {
                questionButton.get(i-1).setBackgroundTintList(getResources().getColorStateList(R.color.defaultColor));
            }

            updateQuestion();

            // Sự kiện khi bấm vào nút
            final int questionNumber = i;
            questionButton.get(i-1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý khi người dùng chọn câu hỏi
                    // Thay đổi màu nút đã chọn và cập nhật currentQuestion
                    saveAnswer();
                    currentQuestion = questionNumber-1;
                    updateQuestion();
                    updateQuestionButtons(questionContainer);
                }
            });

            // Thêm nút vào LinearLayout
            questionContainer.addView(questionButton.get(i-1));
        }
    }

    private void updateQuestion(){
        Exam_Question currentExamQuestion = list.get(currentQuestion);
        questionText.setText(currentExamQuestion.getQuestion());

        if (check){
            for (int i=0;i<4;i++)
                questionRadioButton.get(i).setTextColor(Color.BLACK);
            if (userAnswers.get(currentQuestion)!=-1)
                questionRadioButton.get(userAnswers.get(currentQuestion)-1).setTextColor(Color.RED);
            questionRadioButton.get(correctAnswers.get(currentQuestion)-1).setTextColor(Color.GREEN);
        }
        List<String> options = currentExamQuestion.getOption();
        questionRadioButton.get(0).setText(options.get(0));
        questionRadioButton.get(1).setText(options.get(1));
        questionRadioButton.get(2).setText(options.get(2));
        questionRadioButton.get(3).setText(options.get(3));

        radioGroup.clearCheck();

        int savedAnswer = userAnswers.get(currentQuestion);
        if (savedAnswer != -1) {
            radioGroup.check(savedAnswer); // Đánh dấu lại đáp án đã chọn
        }
    }

    private void createRadioButtons() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        for (int i = 1; i <= 4; i++) {
            RadioButton rb = new RadioButton(this);
            questionRadioButton.add(rb);

            // Thiết lập văn bản cho RadioButton
            questionRadioButton.get(i-1).setTextColor(Color.BLACK);

            // Thiết lập khoảng cách cho RadioButton
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 16, 8, 0); // Khoảng cách giữa các RadioButton
            questionRadioButton.get(i-1).setLayoutParams(params);
            questionRadioButton.get(i-1).setTextSize(20);
            questionRadioButton.get(i-1).setPadding(10,10,10,10);
            questionRadioButton.get(i-1).setTextColor(Color.BLACK);

            if (check) {
                if (i == correctAnswers.get(currentQuestion)) {
                    rb.setTextColor(Color.GREEN); // Màu xanh cho đáp án đúng
                } else if (userAnswers.get(currentQuestion) != null && userAnswers.get(currentQuestion) == i) {
                    rb.setTextColor(Color.RED); // Màu đỏ cho đáp án sai đã chọn
                }
            }

            // Thêm RadioButton vào RadioGroup
            radioGroup.addView(questionRadioButton.get(i-1));
        }
        updateQuestion();
    }

    private void saveAnswer() {
        // Lấy ID của RadioButton được chọn
        int selectedId = radioGroup.getCheckedRadioButtonId();
        userAnswers.set(currentQuestion, selectedId); // Lưu ID vào danh sách
    }




}