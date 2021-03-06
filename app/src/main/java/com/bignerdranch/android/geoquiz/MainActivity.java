package com.bignerdranch.android.geoquiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String KEY_INDEX = "index";
    public static final int RESULT_CODE_CHEAT = 0;

    ActivityResultLauncher<Intent> activityResultLauncher;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button previosButton;
    private Button cheatButton;

    private TextView questionTextView;
    private QuizViewModel quizViewModel;


    public MainActivity() {
        this.activityResultLauncher = null;

        this.trueButton = null;
        this.falseButton = null;
        this.nextButton = null;
        this.previosButton = null;
        this.cheatButton = null;

        this.questionTextView = null;
        this.quizViewModel = null;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle savedInstanceState) called");
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            quizViewModel.moveCurrentIndex(savedInstanceState.getInt(KEY_INDEX, 0));
        }


        quizViewModel = QuizViewModel.getInstance();
        Log.d(TAG, "GOT a QuizViewModel: $quizViewModel");


        questionTextView = findViewById(R.id.question_text_view);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        previosButton = findViewById(R.id.previous_button);
        cheatButton = findViewById(R.id.cheat_button);


        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_CODE_CHEAT){
                Intent intent = result.getData();
                if(intent != null){
                    quizViewModel.setCheater(intent.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false));
                }
            }
        });

        questionTextView.setOnClickListener(view -> {
            quizViewModel.moveCurrentIndex(1);
            updateQuestion();
        });

        trueButton.setOnClickListener(view -> {
            checkAnswer(true);
        });

        falseButton.setOnClickListener(view -> {
            checkAnswer(false);
        });

        nextButton.setOnClickListener(view -> {
            quizViewModel.moveCurrentIndex(1);
            updateQuestion();
        });

        previosButton.setOnClickListener(view -> {
            if(quizViewModel.getCurrentIndex() > 0){
                quizViewModel.moveCurrentIndex(-1);
                updateQuestion();
            }
        });

        cheatButton.setOnClickListener(view -> {
            // start CheatActivity

            boolean answerIsTrue = quizViewModel.getCurrentQuestion().isAnswer();
            Intent intent = CheatActivity.newIntent(getApplicationContext(), answerIsTrue, quizViewModel.isCheater());
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(view, 0, 0, view.getWidth(), view.getHeight());
                activityResultLauncher.launch(intent, options);
            } else{
                activityResultLauncher.launch(intent);
            }
//            startActivity(intent);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, quizViewModel.getCurrentIndex());
    }


    private void updateQuestion() {

        int questionTextResId = quizViewModel.getCurrentQuestion().getTestResId();
        questionTextView.setText(questionTextResId);
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = quizViewModel.getCurrentQuestion().isAnswer();

        int messageResId;


        if(quizViewModel.getCurrentQuestion().isAnswered() == false){
            if(quizViewModel.isCheater()){
                messageResId = R.string.judgment_toast;
            }
            else{
                if(userAnswer == correctAnswer){
                    messageResId = R.string.correct_toast;
                    quizViewModel.setCorrectNum(quizViewModel.getCorrectNum() + 1);
                } else{
                    messageResId = R.string.incorrect_toast;
                }
            }
            quizViewModel.setAnsweredNum(quizViewModel.getAnsweredNum() + 1);
            quizViewModel.getCurrentQuestion().setAnswered(true);
            Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT)
                    .show();
        }

        if(quizViewModel.getAnsweredNum() == quizViewModel.getQuestionBankSize()){
            double result = (double) quizViewModel.getCorrectNum() / quizViewModel.getQuestionBankSize() * 100;
            Toast.makeText(getApplicationContext(), "your score is = " + result, Toast.LENGTH_SHORT)
                    .show();
        }
    }


}