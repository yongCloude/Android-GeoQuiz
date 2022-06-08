package com.bignerdranch.android.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";

    private boolean answerIsTrue = false;

    private CheatViewModel cheatViewModel;

    private TextView answerTextView;
    private TextView apiTextView;
    private TextView cheatCountTextView;
    private Button showAnswerButton;

    public CheatActivity() {
        answerTextView = null;
        apiTextView = null;
        cheatCountTextView = null;
        showAnswerButton = null;
        cheatViewModel = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        cheatViewModel = CheatViewModel.getInstance();
        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        cheatViewModel.setAnswerShown(getIntent().getBooleanExtra(EXTRA_ANSWER_SHOWN, false));

        answerTextView = findViewById(R.id.answer_text_view);
        apiTextView = findViewById(R.id.show_api_version);
        cheatCountTextView = findViewById(R.id.show_cheat_count);
        showAnswerButton = findViewById(R.id.show_answer_button);

        showAnswerButton.setOnClickListener(view -> {
            if(cheatViewModel.getCheatAvailableCount() > 0){
                cheatViewModel.decreaseCheatAvailableCount();
                showAnswerText();
                cheatViewModel.setAnswerShown(true);
                setAnswerShownResult(cheatViewModel.isAnswerShown());
            }
            cheatCountTextView.setText("커닝 가능 횟수 " + cheatViewModel.getCheatAvailableCount());

        });

        cheatCountTextView.setText("커닝 가능 횟수 " + cheatViewModel.getCheatAvailableCount());
        apiTextView.append("API 레벨 " + Build.VERSION.SDK_INT);

        if(cheatViewModel.isAnswerShown()){
            showAnswerText();
            setAnswerShownResult(true);

        }



    }

    private void showAnswerText() {
        int answerText;
        if(answerIsTrue == true){
            answerText = R.string.true_button;
        } else{
            answerText = R.string.false_button;
        }
        answerTextView.setText(answerText);
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent().putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(MainActivity.RESULT_CODE_CHEAT,data);
    }


    public static Intent newIntent(Context packageContext, boolean answerIsTrue, boolean isAnswerShown) {
        return new Intent(packageContext, CheatActivity.class).putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                .putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
    }
}