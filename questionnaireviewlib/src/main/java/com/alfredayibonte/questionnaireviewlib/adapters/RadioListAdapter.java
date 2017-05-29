package com.alfredayibonte.questionnaireviewlib.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alfredayibonte.questionnaireviewlib.R;
import com.alfredayibonte.questionnaireviewlib.RadioListItemView;
import com.alfredayibonte.questionnaireviewlib.models.Answer;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by Alfredayibonte on 5/3/17.
 */
public class RadioListAdapter extends BaseAdapter{
    private  List<Answer> answers = new ArrayList<>();
    private Context context;
    private RadioListAdapter.OnRadioItemClickListener listener;
    private RadioListItemView radioListItemView = null;

    public RadioListAdapter(Context context, List<Answer> answers) {
        this.context = context;
        this.answers = answers;
    }
    public RadioListAdapter(Context context, List<Answer> answers, RadioListAdapter.OnRadioItemClickListener listener) {
        this(context, answers);
        this.listener = listener;

    }

    public RadioListAdapter(List<String> answers, RadioListAdapter.OnRadioItemClickListener listener, Context context) {
        this(answers, context);
        this.listener = listener;
    }

    public RadioListAdapter(List<String> answers, Context context){
        this.context = context;
        this.answers = new ArrayList<>();
        for(String o : answers)
            this.answers.add(new Answer(o, false));

    }

    public void addListener(RadioListAdapter.OnRadioItemClickListener listener){
        this.listener = listener;
    }

    public interface OnRadioItemClickListener {
        void onRadioItemClick(List<Answer> answers);
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        Answer item = (Answer) getItem(position);
        if (convertView == null) {
            radioListItemView = (RadioListItemView)View.inflate(context, R.layout.radio_list_item, null);
        } else{
            radioListItemView = (RadioListItemView)convertView;
        }
        final AppCompatRadioButton radio = (AppCompatRadioButton)radioListItemView.findViewById(R.id.radio);
        radio.setChecked(item.isChecked());
        final TextView textView = (TextView)radioListItemView.findViewById(R.id.tv1);
        final RadioListItemView finalRadioListItemView = radioListItemView;
        textView.setText(item.getAnswer());

        radioListItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswer(position, finalRadioListItemView);
            }
        });
        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswer(position, finalRadioListItemView);
            }
        });

        return radioListItemView;
    }

    private void setAnswer(int position, final RadioListItemView radioListItemView){

        for(Answer o : answers){
            o.setChecked(false);
        }
        Answer answer = answers.get(position);
        answer.setChecked(true);
        radioListItemView.setAnswer(answer);
        if(listener != null)
            listener.onRadioItemClick(this.answers);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Answer getItem(int position) {
        return (answers == null)? null: answers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public List<Answer> getAnswers () {
        return this.answers;
    }

    public void forceReload() {
        notifyDataSetChanged();
    }
}
