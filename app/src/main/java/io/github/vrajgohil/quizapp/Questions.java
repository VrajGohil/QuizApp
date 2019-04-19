package io.github.vrajgohil.quizapp;

public class Questions {
    public int q_id;
    public String question;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public int answer;
    public boolean state;
    public Questions(){

    }
    public Questions(int q_id,String question,String option1,String option2,String option3,String option4,int answer, boolean state){
        this.q_id=q_id;
        this.question=question;
        this.option1=option1;
        this.option2=option2;
        this.option3=option3;
        this.option4=option4;
        this.answer=answer;
        this.state=state;
    }

}
