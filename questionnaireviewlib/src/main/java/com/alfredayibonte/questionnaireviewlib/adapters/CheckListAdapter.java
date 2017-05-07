package com.alfredayibonte.questionnaireviewlib.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.alfredayibonte.questionnaireviewlib.CheckListItemView;
import com.alfredayibonte.questionnaireviewlib.R;
import com.alfredayibonte.questionnaireviewlib.models.Answer;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by Alfredayibonte on 5/3/17.
 */
public class CheckListAdapter extends BaseAdapter {
    private  List<Answer> answers = new ArrayList<>();
    private Context context;
    OnCheckItemClickListener listener;
    public CheckListAdapter(Context context, List<Answer> answers) {
        this.context = context;
        this.answers = answers;
    }
    public CheckListAdapter(Context context, List<Answer> answers, OnCheckItemClickListener listener) {
        this(context, answers);
        this.listener = listener;

    }

    public CheckListAdapter( List<Answer> answers, OnCheckItemClickListener listener, Context context) {
        this(context, answers);
        this.listener = listener;
    }



    public interface OnCheckItemClickListener {
        void onCheckItemClick(List<Answer> answers);
    }

    public void addListener(OnCheckItemClickListener listener){
        this.listener = listener;
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Answer answer = answers.get(position);
        CheckListItemView checkItemView = null;
        if (convertView == null){
            checkItemView = (CheckListItemView)View.inflate(context, R.layout.check_list_item, null);
        }
        else{
            checkItemView = (CheckListItemView)convertView;
        }
        final CheckBox checkBox = (CheckBox)checkItemView.findViewById(R.id.checkbox);
        final TextView textView = (TextView)checkItemView.findViewById(R.id.tv1);
        final CheckListItemView finalCheckListItemView = checkItemView;
        textView.setText(answer.getAnswer());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswer(position, finalCheckListItemView, checkBox.isChecked());
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean state = !checkBox.isChecked();
                checkBox.setChecked(state);
                setAnswer( position, finalCheckListItemView, state );
            }
        });
        return checkItemView;
    }

    private void setAnswer(int position, final CheckListItemView checkListItemView, boolean state){
        Answer answer = answers.get(position);
        answer.setChecked(state);
        checkListItemView.setAnswersObject(answer);
        if(listener != null )
            listener.onCheckItemClick(this.answers);
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }


    public void forceReload() {
        notifyDataSetChanged();
    }


}