package com.alfredayibonte.questionnaireviewlib.models;

/**
 *
 * Created by Alfredayibonte on 5/3/17.
 */
public class Answer {

    String answer;
    boolean checkEnabled;
    boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return String.format("{%s , %s}",  answer, String.valueOf(checked));
    }

    public Answer(){
        this("");
    }

    public Answer(String answer){
        this.answer = answer;
        this.checkEnabled = false;
    }

    /**
     *
     * @param answer
     * @param enableCheck
     */
    public Answer(String answer, boolean enableCheck){
        this.answer = answer;
        this.checkEnabled = enableCheck;
    }
}
