package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";

    private boolean answerIsTrue = false;

    private TextView answerTextView;
    private Button showAnswerButton;

    public CheatActivity() {
        answerTextView = null;
        showAnswerButton = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        answerTextView = findViewById(R.id.answer_text_view);
        showAnswerButton = findViewById(R.id.show_answer_button);

        showAnswerButton.setOnClickListener(view -> {
            int answerText;
            if(answerIsTrue == true){
                answerText = R.string.true_button;
            } else{
                answerText = R.string.false_button;
            }

            answerTextView.setText(answerText);
            setAnswerShownResult(true);
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent().putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(MainActivity.RESULT_CODE_CHEAT,data);
    }


    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        return new Intent(packageContext, CheatActivity.class).putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
    }
}