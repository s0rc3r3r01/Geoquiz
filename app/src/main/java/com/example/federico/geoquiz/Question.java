package com.example.federico.geoquiz;

/**
 * Created by federico on 19/11/2016.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question (int textResId, boolean AnswerTrue) {
        mTextResId= textResId;
        mAnswerTrue = AnswerTrue;

    }
}
