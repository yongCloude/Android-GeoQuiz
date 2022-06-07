package com.bignerdranch.android.geoquiz;

public class CheatViewModel {

    private boolean isAnswerShown = false;

    private CheatViewModel(){

    }

    private  static class LazyHoler {
        public static final CheatViewModel INSTANCE = new CheatViewModel();
    }

    public static CheatViewModel getInstance() { return LazyHoler.INSTANCE; }

    public boolean isAnswerShown() {
        return isAnswerShown;
    }

    public void setAnswerShown(boolean answerShown) {
        isAnswerShown = answerShown;
    }
}
