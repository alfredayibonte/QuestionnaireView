package com.alfredayibonte.questionnaireviewlib.models;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Alfredayibonte on 5/3/17.
 */

public class Question {
    private int id;
    private int answer_type;
    private List<Answer> answers;
    private String content;
    public Question(String content){
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(int answer_type) {
        this.answer_type = answer_type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Question(int id, String content){
        this(content);
        this.id = id;
    }
}
