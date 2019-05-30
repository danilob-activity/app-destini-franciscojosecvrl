package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.londonappbrewery.destini.models.Answer;
import com.londonappbrewery.destini.models.Story;

public class MainActivity extends AppCompatActivity {


    TextView mStoryTextView;
    Button mAnswerTop;
    Button mAnswerBottom;
    // TODO: Declare as variaveis aqui:

    //indice corrente da historia
    private Story mStorySelected;

    Story mT1 = new Story(R.sting.T1_Stroy);
    Story mT2 = new Story(R.string.T2_Story);
    Story mT3 = new Story(R.string.T3_Story);
    Story mT4 = new Story(R.string.T4_End);
    Story mT5 = new Story(R.string.T5_End);
    Story mT6 = new Story(R.string.T6_End);

    Answer ansT1_1 = new Answer(R.string.T1_Ans1);
    Answer ansT1_2 = new Answer(R.string.T1_Ans2);
    Answer ansT2_2 = new Answer(R.string.T2_Ans1);
    Answer ansT2_1 = new Answer(R.string.T2_Ans2);
    Answer ansT3_1 = new Answer(R.string.T3_Ans1);
    Answer ansT3_2 = new Answer(R.string.T3_Ans2);

      private int mStoryIndex;

    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("StoryKey",mStoryIndex);
    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("StoryKey",mStoryIndex);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO: Faça o link do layout com a activity
        mStoryTextView = findViewById(R.id.storyTextView);
        mAnswerBottom = findViewById(R.id.buttonBottom);
        mAnswerTop = findViewById(R.id.buttonTop);


        //TODO:faça o mapeamento da história


        mT1.setAnswerTop(ansT1_1);
        mT1.setAnswerBottom(ansT1_2);
        ansT1_1.setChildStory(mT3);

        mT2.setAnswerTop(ansT2_1);
        mT2.setAnswerBottom(ansT2_2);
        ansT2_1.setChildStory(mT3);

        mT3.setAnswerTop(ansT3_1);
        mT3.setAnswerBottom(ansT3_2);
        ansT3_1.setChildStory(mT6);
        ansT3_2.setChildStory(mT5);


        mStoryTextView.setText(mStorySelected.getStoryID());
        mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
        mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());



        // TODO: Coloque o evento do click do botão, caso precise colocar a visibilidade no botão invisivel utilize a função
        // do botão setVisibility(View.GONE):


        mAnswerTop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                mStorySelected = mStorySelected.getAnswerTop().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerTop() == null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }
            }
        });

        mAnswerBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStorySelected = mStorySelected.getAnswerBottom().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerBottom() == null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);

                }else{
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                }
            }
        });

        //onCreate()
        if(savedInstanceState!=null){
            mStoryIndex = savedInstanceState.getInt("StoryKey");
        }
    }

}
