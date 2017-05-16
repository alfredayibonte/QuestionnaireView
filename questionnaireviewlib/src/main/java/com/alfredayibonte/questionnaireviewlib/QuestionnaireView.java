package com.alfredayibonte.questionnaireviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.alfredayibonte.questionnaireviewlib.adapters.CheckListAdapter;
import com.alfredayibonte.questionnaireviewlib.adapters.RadioListAdapter;
import com.alfredayibonte.questionnaireviewlib.models.Answer;
import com.alfredayibonte.questionnaireviewlib.models.Question;
import com.alfredayibonte.questionnaireviewlib.utils.AnswerType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Alfredayibonte on 5/3/17.
 */

public class QuestionnaireView extends RelativeLayout {
    private EditText editTv;
    private WebView webview;
    private ListView listView;
    private int viewType;
    private Question question;
    private List<Answer> answers;
    private RadioListAdapter radioAdapter;
    private CheckListAdapter checkAdapter;
    private View view;
    public QuestionnaireView(Context context) {
        super(context);
    }

    public QuestionnaireView(Context context, AttributeSet attrs) {
        super(context, attrs);
        drawInnerViews(context, attrs);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    private void drawInnerViews(Context context, AttributeSet attrs){
        LayoutParams mainLayoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mainLayoutParams.setMargins(16,16,16,16);
        setLayoutParams(mainLayoutParams);

        //creation & addition of webview
        webview = new WebView(context, attrs);
        webview.setId(android.R.id.content);
        webview.setLayoutParams(
                new LayoutBuilder()
                        .addWidth(LayoutParams.MATCH_PARENT)
                        .addHeight(LayoutParams.WRAP_CONTENT)
                        .setMargin(0,40,0,0)
                        .create()
        );
        webview.getSettings();
        webview.setBackgroundColor(Color.argb(0,0,0,0));
        addView(webview);

        //creation of list view
        listView = new ListView(context, attrs);
        listView.setId(android.R.id.list);
        listView.setLayoutParams(
                new LayoutBuilder()
                        .addWidth(LayoutParams.MATCH_PARENT)
                        .addHeight(LayoutParams.WRAP_CONTENT)
                        .setMargin(0,10,0,0)
                        .addRule(BELOW, webview.getId() )
                        .create()
        );
        addView(listView );

        //creation & addition of editText
        editTv = new EditText(context, attrs);
        editTv.setVisibility(GONE);
        editTv.setId(android.R.id.text1);
        editTv.setLayoutParams(
                new LayoutBuilder()
                        .addWidth(LayoutParams.MATCH_PARENT)
                        .addHeight(LayoutParams.WRAP_CONTENT)
                        .setMargin(0, 10, 0, 0)
                        .addRule(BELOW, webview.getId())
                        .create()
        );
        editTv.setInputType(InputType.TYPE_CLASS_TEXT);
        editTv.setImeOptions(EditorInfo.IME_ACTION_DONE);
        addView(editTv );

    }

    public QuestionnaireView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.QuestionBaseView);
        int position = values.getInt(R.styleable.QuestionBaseView_view_type, 1);
        setViewType(position);
        String text = values.getString(R.styleable.QuestionBaseView_question);
        setQuestion(text);
        CharSequence[] answers =  values.getTextArray(R.styleable.QuestionBaseView_entries);
        if(answers != null) setAnswers(answers);
        values.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    public void setViewType(int viewType){
        this.viewType = viewType;
        if(question != null)
            question.setAnswer_type(viewType);
        switch (viewType){
            case AnswerType.RADIO:
            case AnswerType.CHECKLIST:
                makeListViewVisible();
                break;
            case AnswerType.EDITTEXT:
                listView.setVisibility(GONE);
                editTv.setVisibility(VISIBLE);
                break;
        }

    }


    public List<Answer> getAnswers(){
        return this.question.getAnswers();
    }

    public String getQuestionText () {
        return this.question.getContent();
    }

    public void setQuestion(String content){
        if(question != null) question.setContent(content);
        webview.loadDataWithBaseURL("file:///android_asset/",
                content, "text/html", "utf-8", null);
    }

    public void setAnswers(List<Answer> answers){
        this.answers = answers;
        switch (viewType){
            case AnswerType.CHECKLIST:
                checkAdapter = new CheckListAdapter(getContext(), this.answers);
                listView.setAdapter(checkAdapter);
                break;
            case AnswerType.RADIO:
                radioAdapter = new RadioListAdapter(getContext(), this.answers);
                listView.setAdapter(radioAdapter);
                break;
        }
        if(question != null && !answers.isEmpty())
            question.setAnswers(this.answers);
    }

    public void setAnswers(CharSequence[] answers){
        this.answers = new ArrayList<>();
        for(CharSequence item : answers)
            this.answers.add(new Answer(String.valueOf(item)));
        setAnswers(this.answers);
    }

    /**
     *
     * @param answers
     */
    public void setAnswers(ArrayList<String> answers){
        this.answers = new ArrayList<>();
        for(String item : answers)
            this.answers.add(new Answer(item));
        setAnswers(this.answers);
    }


    public void addCheckItemListener(CheckListAdapter.OnCheckItemClickListener checkListener) {
        if(checkAdapter != null )
            checkAdapter.addListener(checkListener);
    }

    /**
     *
     * @param radioListener
     */
    public void addRadioItemListener(RadioListAdapter.OnRadioItemClickListener radioListener) {
        if (radioAdapter != null)
            radioAdapter.addListener(radioListener);
    }

    /**
     *
     * @param editorActionListener
     */
    public void addOnEditorActionListener(EditText.OnEditorActionListener editorActionListener){
        editTv.setOnEditorActionListener(editorActionListener);
    }

    public void addTextChangedListener(TextWatcher textWatcher){
        editTv.addTextChangedListener(textWatcher);
    }


    public String getTextFromEditText(){
        return editTv.getText().toString();
    }



    private void makeListViewVisible(){
        listView.setVisibility(VISIBLE);
        editTv.setVisibility(GONE);
    }

    /**
     *
     * @param layout layout file (eg. R.layout.footer)
     */
    public void addFooter(int layout){
        View view = View.inflate(getContext(), layout, null);
        addFooter(view);
    }

    /**
     *
     * @param view adds view to parent bottom
     */
    public void addFooter(View view){
        if( view == null ) return;
        view.setBackgroundColor(ContextCompat.getColor(getContext(),
                android.R.color.transparent));
        view.setLayoutParams(
                new LayoutBuilder()
                        .addWidth(LayoutParams.MATCH_PARENT)
                        .addHeight(LayoutParams.WRAP_CONTENT)
                        .addRule(ALIGN_PARENT_BOTTOM, getId())
                        .create()
        );
        addView(view);


    }

    public Question getQuestion(){
        return  question != null ? question : new Question("");
    }

    private class LayoutBuilder {
        LayoutParams layoutParams;
        int width = LayoutParams.MATCH_PARENT;
        int height = LayoutParams.MATCH_PARENT;
        int left = 0, top = 0, right = 0, bottom = 0;
        int verb = 0, subject = 0;
        boolean isRuleAdded = false;

        private LayoutBuilder(){

        }

        private LayoutBuilder setMargin(int left, int top, int right, int bottom){
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            return this;
        }

        /**
         *
         * @param width eg. LayoutParams.MATCH_PARENT
         * @return this instance of LayoutBuilder
         */
        private LayoutBuilder addWidth(int width){
            this.width = width;
            return this;
        }

        /**
         *
         * @param height eg. LayoutParams.MATCH_PARENT
         * @return this instance of LayoutBuilder
         */
        private LayoutBuilder addHeight(int height){
            this.height = height;
            return this;
        }

        private LayoutBuilder addRule(int verb, int subject){
            isRuleAdded = true;
            this.verb = verb;
            this.subject = subject;
            return this;
        }

        private LayoutParams create(){
            layoutParams = new LayoutParams(width, height);
            layoutParams.setMargins(left, top, right, bottom);
            if(isRuleAdded) layoutParams.addRule(verb, subject);
            return layoutParams;
        }
    }

}
