package com.alfredayibonte.questionnaireviewlib;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.alfredayibonte.questionnaireviewlib.adapters.CheckListAdapter;
import com.alfredayibonte.questionnaireviewlib.models.Answer;


/**
 *
 *  Created by Alfredayibonte on 5/3/17.
 */
public class CheckListItemView extends LinearLayout{
    private AppCompatCheckBox checkBox;
    private AppCompatTextView textView;
    private Answer answer;
    private Context context;
    private CheckListAdapter adapter;
    private boolean isChecked = false;
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswersObject(Answer answer) {
        this.answer = answer;
        textView.setText(answer.getAnswer());
        this.context = getContext();
    }
    public void setCheckItem(Answer answersObject, CheckListAdapter adapter) {
        this.setAnswersObject(answersObject);
        this.adapter = adapter;
    }

    public CheckListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        checkBox = (AppCompatCheckBox)findViewById(R.id.checkbox);
        textView = (AppCompatTextView)findViewById(R.id.tv1);

    }


}
