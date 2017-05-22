package com.alfredayibonte.questionnaireviewlib;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.alfredayibonte.questionnaireviewlib.adapters.RadioListAdapter;
import com.alfredayibonte.questionnaireviewlib.models.Answer;


/**
 *
 *  Created by Alfredayibonte on 5/3/17.
 */
public class RadioListItemView extends LinearLayout{
    private AppCompatRadioButton radioButton;
    private AppCompatTextView textView;
    private Answer answer;
    private Context context;
    private RadioListAdapter adapter;
    private boolean isChecked = false;
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        textView.setText(answer.getAnswer());
        this.context = getContext();
    }
    public void setRadioItem(Answer answer, RadioListAdapter adapter) {
        this.setAnswer(answer);
        this.adapter = adapter;
    }

    public RadioListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        radioButton = (AppCompatRadioButton) findViewById(R.id.radio);
        textView = (AppCompatTextView)findViewById(R.id.tv1);

    }


}
