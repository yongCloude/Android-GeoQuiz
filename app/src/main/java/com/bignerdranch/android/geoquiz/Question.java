package com.bignerdranch.android.geoquiz;

import androidx.annotation.StringRes;

public class Question {

    @StringRes
    private final int testResId;
    private final boolean answer;
    private boolean answered;

    public Question(int testResId, boolean answer) {
        this.testResId = testResId;
        this.answer = answer;
        this.answered = false;
    }

    public int getTestResId() {
        return testResId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
