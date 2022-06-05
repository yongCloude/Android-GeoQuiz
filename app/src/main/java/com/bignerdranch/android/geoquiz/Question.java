package com.bignerdranch.android.geoquiz;

import androidx.annotation.StringRes;

public class Question {

    @StringRes
    private final int testResId;
    private final boolean answer;

    public Question(int testResId, boolean answer) {
        this.testResId = testResId;
        this.answer = answer;
    }

    public int getTestResId() {
        return testResId;
    }

    public boolean isAnswer() {
        return answer;
    }
}
