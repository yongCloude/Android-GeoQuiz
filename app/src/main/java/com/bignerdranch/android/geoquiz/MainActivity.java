package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;

    private TextView questionTextView;

    private List<Question> questionBank;
    private int currentIndex;

    public MainActivity() {

        this.questionTextView = null;
        this.trueButton = null;
        this.falseButton = null;
        this.nextButton = null;
        this.currentIndex = 0;

        questionBank = new ArrayList<>();
        questionBank.add(new Question(R.string.question_australia, true));
        questionBank.add(new Question(R.string.question_oceans, true));
        questionBank.add(new Question(R.string.question_mideast, false));
        questionBank.add(new Question(R.string.question_africa, false));
        questionBank.add(new Question(R.string.question_americas, true));
        questionBank.add(new Question(R.string.question_asia, true));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionTextView = findViewById(R.id.question_text_view);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);

        trueButton.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT)
                    .show();
        });

        falseButton.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT)
                    .show();
        });

        nextButton.setOnClickListener(view -> {
            currentIndex = (currentIndex + 1) % questionBank.size();
            updateQuestion();
        });

        updateQuestion();

    }

    private void updateQuestion() {
        int questionTextResId = questionBank.get(currentIndex).getTestResId();
        questionTextView.setText(questionTextResId);
    }
}