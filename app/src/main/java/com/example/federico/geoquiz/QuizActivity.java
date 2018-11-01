package com.example.federico.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX="index";
    private static final int REQUEST_CODE_CHEAT=0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private boolean mIsCheater;

    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {

            new Question(R.string.question_africa, true),
            new Question(R.string.question_oceans, true)
    };

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer (boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;

        if (mIsCheater) {
            messageResId = R.string.judgement_toast;
        }


        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toas;
        }
        else {
            messageResId = R.string.uncorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"OnCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

    mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


     mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                checkAnswer(true);

                //does nothing
            }
        });
     mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);

                //does nothing
            }
        });

    mCheatButton = (Button) findViewById(R.id.cheat_button);
       mCheatButton.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               // Start cheatActivity
               Intent i;
               boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
               i = new CheatActivity().newIntent(QuizActivity.this,answerIsTrue);

                    //   newIntent(QuizActivity.this,answerIsTrue);
               startActivityForResult(i,REQUEST_CODE_CHEAT);
           }

       });

    mNextButton = (Button) findViewById(R.id.next_button);
    mNextButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
           mCurrentIndex= (mCurrentIndex +1) % mQuestionBank.length;
            mIsCheater = false;
            updateQuestion();
        }
    });

    if (savedInstanceState != null){

        mCurrentIndex=savedInstanceState.getInt(KEY_INDEX);

    }
    updateQuestion();

    }
    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (resultCode == REQUEST_CODE_CHEAT) {

            if (data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }


    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() Called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() Called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() Called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() Called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() Called");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstance() Called");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }
}
