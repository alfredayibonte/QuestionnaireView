package com.alfredayibonte.questionnaireview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.alfredayibonte.questionnaireviewlib.QuestionnaireView;
import com.alfredayibonte.questionnaireviewlib.adapters.CheckListAdapter;
import com.alfredayibonte.questionnaireviewlib.adapters.RadioListAdapter;
import com.alfredayibonte.questionnaireviewlib.models.Answer;
import com.alfredayibonte.questionnaireviewlib.models.Question;
import java.util.ArrayList;
import java.util.List;
import static com.alfredayibonte.questionnaireview.QuestionPagerAdapter.ANSWERS_KEY;
import static com.alfredayibonte.questionnaireview.QuestionPagerAdapter.ANSWER_TYPE_KEY;
import static com.alfredayibonte.questionnaireview.QuestionPagerAdapter.POSITION_KEY;
import static com.alfredayibonte.questionnaireview.QuestionPagerAdapter.QUESTION_KEY;

public class QuestionFragment extends Fragment implements TextWatcher,
        CheckListAdapter.OnCheckItemClickListener, RadioListAdapter.OnRadioItemClickListener {
    OnQuestionItemSelectedListener mCallback;
    Question question;
    ArrayList<String> answers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        question = new Question("");
        if (getArguments() != null) {
            question.setId(getArguments().getInt(POSITION_KEY));
            question.setContent(getArguments().getString(QUESTION_KEY));
            question.setAnswer_type(getArguments().getInt(ANSWER_TYPE_KEY));
            answers = getArguments().getStringArrayList(ANSWERS_KEY);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.question_fragment, container, false);
        QuestionnaireView questionnaireView = (QuestionnaireView)rootView.findViewById(R.id.questionnaire);
        questionnaireView.setQuestion(question.getContent());
        questionnaireView.setViewType(question.getAnswer_type());
        questionnaireView.setAnswers(answers);
        questionnaireView.addRadioItemListener(this);
        questionnaireView.addCheckItemListener(this);
        questionnaireView.addTextChangedListener(this);
        View view = View.inflate(getContext(), R.layout.footer, null);
        Button next = (Button) view.findViewById(R.id.next);
        Button back = (Button) view.findViewById(R.id.back);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onNextButtonPressed(question);
            }
        });
        questionnaireView.addFooter(view);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onPreviousButtonPressed(question);
            }
        });

        return rootView;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.e(MainActivity.class.getSimpleName(), charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckItemClick(List<Answer> answers) {
        //todo: get answers here if check list
    }

    @Override
    public void onRadioItemClick(List<Answer> answers) {
        //todo: get answers here if radio
    }

    public interface OnQuestionItemSelectedListener{
        public void onPreviousButtonPressed(Question question);
        public void onNextButtonPressed(Question question);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        AppCompatActivity activity = null;

        if (context instanceof AppCompatActivity){
            activity = (AppCompatActivity) context;
        }
        if(activity != null){
            try {
                mCallback = (OnQuestionItemSelectedListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }

    }
}
