package com.alfredayibonte.questionnaireview;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alfredayibonte.questionnaireviewlib.models.Question;

public class MainActivity extends AppCompatActivity implements QuestionFragment.OnQuestionItemSelectedListener {
    private NonSwipeableViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = (NonSwipeableViewPager)findViewById(R.id.pager);
        AppController app = (AppController) getApplication();
        mPagerAdapter = new QuestionPagerAdapter(getApplicationContext(),
                getSupportFragmentManager(), app.getQuestions());
        mPager.setAdapter(mPagerAdapter);

    }

    @Override
    public void onPreviousButtonPressed(Question question) {
        int page = mPager.getCurrentItem();
        mPager.setCurrentItem( page - 1 );
    }

    @Override
    public void onNextButtonPressed(Question question) {
        int page = mPager.getCurrentItem();
        mPager.setCurrentItem( page + 1 );
    }
}