package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button previosButton;

    private TextView questionTextView;

    private List<Question> questionBank;
    private int currentIndex;

    public MainActivity() {

        this.questionTextView = null;
        this.trueButton = null;
        this.falseButton = null;
        this.nextButton = null;
        this.previosButton = null;
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
        Log.d(TAG, "onCreate(Bundle savedInstanceState) called");
        setContentView(R.layout.activity_main);


        questionTextView = findViewById(R.id.question_text_view);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        previosButton = findViewById(R.id.previous_button);

        questionTextView.setOnClickListener(view -> {
            moveIndex((currentIndex + 1) % questionBank.size());
            updateQuestion();
        });

        trueButton.setOnClickListener(view -> {
            checkAnswer(true);
        });

        falseButton.setOnClickListener(view -> {
            checkAnswer(false);
        });

        nextButton.setOnClickListener(view -> {
            moveIndex((currentIndex + 1) % questionBank.size());
            updateQuestion();
        });

        previosButton.setOnClickListener(view -> {
            if(currentIndex > 0){
                moveIndex((currentIndex - 1) % questionBank.size());
                updateQuestion();
            }
        });


        updateQuestion();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void moveIndex(int i) {
        currentIndex = i;
    }

    private void updateQuestion() {
        int questionTextResId = questionBank.get(currentIndex).getTestResId();
        questionTextView.setText(questionTextResId);
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = questionBank.get(currentIndex).isAnswer();

        int messageResId;
        if(userAnswer == correctAnswer){
            messageResId = R.string.correct_toast;
        } else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_LONG)
                .show();
    }


}