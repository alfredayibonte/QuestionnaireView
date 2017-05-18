package com.alfredayibonte.questionnaireview;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPreviousButtonPressed() {
        int page = mPager.getCurrentItem();
        mPager.setCurrentItem( page - 1 );
    }

    @Override
    public void onNextButtonPressed() {
        int page = mPager.getCurrentItem();
        mPager.setCurrentItem( page + 1 );
    }
}