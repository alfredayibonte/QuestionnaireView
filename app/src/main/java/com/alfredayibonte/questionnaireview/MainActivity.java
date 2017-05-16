package com.alfredayibonte.questionnaireview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alfredayibonte.questionnaireviewlib.QuestionnaireView;
import com.alfredayibonte.questionnaireviewlib.utils.AnswerType;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionnaireView questionnaireView = (QuestionnaireView)findViewById(R.id.questionnaire);
        questionnaireView.setQuestion("<h1 style='color: red;'>What is the name of this library ?</h1>");
        questionnaireView.setViewType(AnswerType.EDITTEXT);
        questionnaireView.addTextChangedListener(this);
        View view = View.inflate(this, R.layout.footer, null);
        Button btn = (Button) view.findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("NEXT::", "Next button clicked");
            }
        });
        questionnaireView.addFooter(view);

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
}