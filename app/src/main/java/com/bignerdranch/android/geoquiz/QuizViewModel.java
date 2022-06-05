package com.bignerdranch.android.geoquiz;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class QuizViewModel extends ViewModel {
    public static final String TAG = "QuizViewModel";

    public QuizViewModel() {
        Log.d(TAG, "ViewModel instance created!");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "ViewModel instance about to be destroyed");
    }
}
