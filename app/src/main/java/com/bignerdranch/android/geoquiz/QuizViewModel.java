package com.bignerdranch.android.geoquiz;


import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizViewModel extends ViewModel {
    public static final String TAG = "QuizViewModel";

    private List<Question> questionBank;
    private int currentIndex;
    private int answeredNum;
    private int correctNum;


    private QuizViewModel() {
        Log.d(TAG, "ViewModel instance created!");

        this.currentIndex = 0;
        this.answeredNum = 0;
        this.correctNum = 0;

        questionBank = new ArrayList<>();
        questionBank.add(new Question(R.string.question_australia, true));
        questionBank.add(new Question(R.string.question_oceans, true));
        questionBank.add(new Question(R.string.question_mideast, false));
        questionBank.add(new Question(R.string.question_africa, false));
        questionBank.add(new Question(R.string.question_americas, true));
        questionBank.add(new Question(R.string.question_asia, true));
    }

    private static class LazyHolder {
        public static final QuizViewModel INSTANCE = new QuizViewModel();
    }

    public static QuizViewModel getInstance(){
        return LazyHolder.INSTANCE;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "ViewModel instance about to be destroyed");
    }

    public Question getCurrentQuestion() {
        return questionBank.get(currentIndex);
    }

    public int getQuestionBankSize(){
        return questionBank.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void moveCurrentIndex(int direction) {
        this.currentIndex = (this.currentIndex + direction) % questionBank.size();
    }

    public int getAnsweredNum() {
        return answeredNum;
    }

    public void setAnsweredNum(int answeredNum) {
        this.answeredNum = answeredNum;
    }

    public int getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(int correctNum) {
        this.correctNum = correctNum;
    }
}
