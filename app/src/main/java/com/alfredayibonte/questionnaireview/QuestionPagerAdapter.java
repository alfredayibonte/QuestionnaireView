package com.alfredayibonte.questionnaireview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alfredayibonte.questionnaireview.models.ResultObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionPagerAdapter extends FragmentStatePagerAdapter {
    Fragment fragment;
    Context context;
    protected static final String POSITION_KEY = "POSITION";
    protected static final String QUESTION_KEY = "QUESTION";
    protected static final String ANSWERS_KEY = "ANSWERS";
    protected static final String ANSWER_TYPE_KEY = "ANSWER_TYPE";
    List<ResultObject> questions;
    public QuestionPagerAdapter(Context context,
                                FragmentManager fm){
        super(fm);
        this.context = context;
        questions = new ArrayList<>();
    }

    public QuestionPagerAdapter(Context context, FragmentManager fm, List<ResultObject> questions){
        this(context, fm);
        this.questions = questions;
    }

    @Override
    public Fragment getItem(int position) {
        fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION_KEY, position);
        if(questions == null || questions.isEmpty())
            return fragment;
        else if(questions.get(position) == null ) return fragment;
        args.putString(QUESTION_KEY, questions.get(position).getContent());
        args.putStringArrayList(ANSWERS_KEY, questions.get(position).getAnswers());
        args.putInt(ANSWER_TYPE_KEY, questions.get(position).getAnswer_type());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
      return (questions != null && !questions.isEmpty())? questions.size() : 1;
    }



}


