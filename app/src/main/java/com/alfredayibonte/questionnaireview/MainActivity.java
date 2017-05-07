package com.alfredayibonte.questionnaireview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.alfredayibonte.questionnaireviewlib.QuestionnaireView;
import com.alfredayibonte.questionnaireviewlib.adapters.CheckListAdapter;
import com.alfredayibonte.questionnaireviewlib.adapters.RadioListAdapter;
import com.alfredayibonte.questionnaireviewlib.models.Answer;
import com.alfredayibonte.questionnaireviewlib.utils.AnswerType;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        CheckListAdapter.OnCheckItemClickListener,
        RadioListAdapter.OnRadioItemClickListener, TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionnaireView questionnaireView = (QuestionnaireView)findViewById(R.id.questionnaire);
        questionnaireView.setQuestion("What is the name of moses' father ?");
        questionnaireView.setViewType(AnswerType.EDITTEXT);
        questionnaireView.addRadioItemListener(this);
        questionnaireView.addCheckItemListener(this);
        questionnaireView.addOnEditorActionListener(this);
    }

    @Override
    public void onCheckItemClick(List<Answer> answers) {
        Log.e("check answers:", answers.toString());
    }

    @Override
    public void onRadioItemClick(List<Answer> answers) {
        Log.e("radio answers: ", answers.toString());
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

        return (i == EditorInfo.IME_ACTION_DONE);
    }
}
