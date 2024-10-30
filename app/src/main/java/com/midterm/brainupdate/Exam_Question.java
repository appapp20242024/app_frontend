package com.midterm.brainupdate;

import java.util.ArrayList;
import java.util.List;

public class Exam_Question {
    private String question;
    private List<String> option;
    private String answer;

    public Exam_Question(String question, List<String> option, String answer) {
        this.question = question;
        this.option = option;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public List<String> getOption(){
        return option;
    }

    public void setOption(List<String> option){
        this.option = option;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
}
