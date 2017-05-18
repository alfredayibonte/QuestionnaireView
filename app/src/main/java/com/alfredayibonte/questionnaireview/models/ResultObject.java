package com.alfredayibonte.questionnaireview.models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * Created by Alfredayibonte on 5/18/17.
 */

public class ResultObject {

    public ResultObject(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public int getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(int answer_type) {
        this.answer_type = answer_type;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    private int id;
    private String content;
    private ArrayList<String> answers;
    private ArrayList<String> answer;
    private int answer_type;
    private int difficulty;
    private Date created_at;
    private Date updated_at;
}
