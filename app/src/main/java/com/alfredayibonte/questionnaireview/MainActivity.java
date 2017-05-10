package com.alfredayibonte.questionnaireview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alfredayibonte.questionnaireviewlib.QuestionnaireView;
import com.alfredayibonte.questionnaireviewlib.adapters.RadioListAdapter;
import com.alfredayibonte.questionnaireviewlib.models.Answer;
import com.alfredayibonte.questionnaireviewlib.utils.AnswerType;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        RadioListAdapter.OnRadioItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionnaireView questionnaireView = (QuestionnaireView)findViewById(R.id.questionnaire);
        questionnaireView.setQuestion("What is the name of this library ?");
        questionnaireView.setViewType(AnswerType.RADIO);
        CharSequence[] answers = new CharSequence[]{
                "Questionnaire", "QuestionnaireView", "Question"};
        questionnaireView.setAnswers(answers);
        questionnaireView.addRadioItemListener(this);
    }


    @Override
    public void onRadioItemClick(List<Answer> answers) {
        Log.e("radio answers: ", answers.toString());
    }
}
