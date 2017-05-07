package com.alfredayibonte.questionnaireview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.alfredayibonte.questionnaireviewlib.QuestionnaireView;
import com.alfredayibonte.questionnaireviewlib.adapters.CheckListAdapter;
import com.alfredayibonte.questionnaireviewlib.adapters.RadioListAdapter;
import com.alfredayibonte.questionnaireviewlib.models.Answer;
import com.alfredayibonte.questionnaireviewlib.utils.AnswerType;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        CheckListAdapter.OnCheckItemClickListener,
        RadioListAdapter.OnRadioItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionnaireView questionnaireView = (QuestionnaireView)findViewById(R.id.questionnaire);
        questionnaireView.setQuestion("What is the name of moses' father ?");
        questionnaireView.setViewType(AnswerType.RADIO);
        questionnaireView.addRadioItemListener(this);
        questionnaireView.addCheckItemListener(this);
    }

    @Override
    public void onCheckItemClick(List<Answer> answers) {
        Log.e("check answers:", answers.toString());
    }

    @Override
    public void onRadioItemClick(List<Answer> answers) {
        Log.e("radio answers: ", answers.toString());
    }
}
