package io.github.vrajgohil.quizapp;

public class Questions {
    public String q_id;
    public String question;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public int answer;
    public int currentState;
    public Questions(){

    }
    public Questions(String q_id,String question,String option1,String option2,String option3,String option4,int answer, int currentState){
        this.q_id=q_id;
        this.question=question;
        this.option1=option1;
        this.option2=option2;
        this.option3=option3;
        this.option4=option4;
        this.answer=answer;
        this.currentState=currentState;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption4() {
        return option4;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption1() {
        return option1;
    }

    public int getAnswer() {
        return answer;
    }

    public String getQ_id() {
        return q_id;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }
}
