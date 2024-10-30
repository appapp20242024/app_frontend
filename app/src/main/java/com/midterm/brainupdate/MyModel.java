package com.midterm.brainupdate;

public class MyModel {
    private int id;
    private int module_id;
    private String question;
    private String answer;
//    private Boolean pickedstar;
//    private String create_at;
//    private String update_at;

    public MyModel(int id, int module_id, String question, String answer){
        this.id = id;
        this.module_id = module_id;
        this.question = question;
        this.answer = answer;
    }
    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModule_id(){
        return module_id;
    }

    public void setModule_id(int module_id){
        this.module_id = module_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

//    public Boolean getPickedstar(){
//        return pickedstar;
//    }
//
//    public void setPickedstar(Boolean pickedstar){
//        this.pickedstar = pickedstar;
//    }
//
//    public String getCreate_at(){
//        return create_at;
//    }
//
//    public void setCreate_at(String create_at){
//        this.create_at = create_at;
//    }
//
//    public String getUpdate_at(){
//        return update_at;
//    }
//
//    public void setUpdate_at(String update_at){
//        this.update_at = update_at;
//    }
}
