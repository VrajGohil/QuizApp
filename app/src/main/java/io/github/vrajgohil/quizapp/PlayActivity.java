package io.github.vrajgohil.quizapp;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    private TextView questionView;
    private Button buttonOption1;
    private Button buttonOption2;
    private Button buttonOption3;
    private Button buttonOption4;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    DatabaseReference reference;
    int total=0;
    int correct=0;
    int time;
    Random random;
    int randomNumber;
    String scoreId;
    CountDownTimer countDownTimer;
    boolean checkToUpdate=true;

    private ArrayList<Integer> randomCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        questionView = findViewById(R.id.questionView);
        buttonOption1=findViewById(R.id.buttonOption1);
        buttonOption2=findViewById(R.id.buttonOption2);
        buttonOption3=findViewById(R.id.buttonOption3);
        buttonOption4=findViewById(R.id.buttonOption4);
        progressBar=findViewById(R.id.progressBarTimer);
        progressDialog = new ProgressDialog(this);

        buttonOption1.setBackgroundColor(Color.parseColor("#03A9F4"));
        buttonOption2.setBackgroundColor(Color.parseColor("#03A9F4"));
        buttonOption3.setBackgroundColor(Color.parseColor("#03A9F4"));
        buttonOption4.setBackgroundColor(Color.parseColor("#03A9F4"));
        randomCheck=new ArrayList<>();
        randomCheck.clear();
        random=new Random();

        Intent passIntent=getIntent();
        passIntent.getStringExtra("scoreId");


        countDownTimer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds =(int)(millisUntilFinished/1000);
                time=seconds;
                progressBar.incrementProgressBy(1);
            }

            @Override
            public void onFinish() {
                updateQuestions();
            }
        };



    }

    private void updateQuestions() {
        randomNumber = random.nextInt(24);
        if (!randomCheck.contains(randomNumber)) {
            total++;
            randomCheck.add(randomNumber);
            progressBar.setProgress(0);
            buttonOption1.setClickable(true);
            buttonOption2.setClickable(true);
            buttonOption3.setClickable(true);
            buttonOption4.setClickable(true);
            if (total > 10) {
                countDownTimer.cancel();
                if(checkToUpdate){
                    updateScore();
                    checkToUpdate=false;
                }
                Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
                intent.putExtra("correct", String.valueOf(correct));
                startActivity(intent);
                finish();
            } else {
                progressDialog.setMessage("Question Loading");
                progressDialog.show();
                reference = FirebaseDatabase.getInstance().getReference().child("questions").child(String.valueOf(randomNumber));
                reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Questions question = dataSnapshot.getValue(Questions.class);
                    progressDialog.dismiss();
                    countDownTimer.start();
                    questionView.setText("Q"+String.valueOf(total)+". "+question.getQuestion());
                    buttonOption1.setText(question.getOption1());
                    buttonOption2.setText(question.getOption2());
                    buttonOption3.setText(question.getOption3());
                    buttonOption4.setText(question.getOption4());

                    buttonOption1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            countDownTimer.cancel();
                            buttonOption1.setClickable(false);
                            buttonOption2.setClickable(false);
                            buttonOption3.setClickable(false);
                            buttonOption4.setClickable(false);
                            if (buttonOption1.getText().toString().equals(question.getAnswer())) {
                                buttonOption1.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct+=correct+5*time;
                                        buttonOption1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            } else {

                                buttonOption1.setBackgroundColor(Color.RED);
                                if (buttonOption2.getText().toString().equals(question.getAnswer())) {
                                    buttonOption2.setBackgroundColor(Color.GREEN);
                                } else if (buttonOption3.getText().toString().equals(question.getAnswer())) {
                                    buttonOption3.setBackgroundColor(Color.GREEN);
                                } else if (buttonOption4.getText().toString().equals(question.getAnswer())) {
                                    buttonOption4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        buttonOption1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            }

                        }
                    });

                    buttonOption2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            countDownTimer.cancel();
                            buttonOption1.setClickable(false);
                            buttonOption2.setClickable(false);
                            buttonOption3.setClickable(false);
                            buttonOption4.setClickable(false);
                            if (buttonOption2.getText().toString().equals(question.getAnswer())) {
                                buttonOption2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct+=correct+5*time;
                                        buttonOption2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            } else {

                                buttonOption2.setBackgroundColor(Color.RED);
                                if (buttonOption1.getText().toString().equals(question.getAnswer())) {
                                    buttonOption1.setBackgroundColor(Color.GREEN);
                                } else if (buttonOption3.getText().toString().equals(question.getAnswer())) {
                                    buttonOption3.setBackgroundColor(Color.GREEN);
                                } else if (buttonOption4.getText().toString().equals(question.getAnswer())) {
                                    buttonOption4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        buttonOption1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            }
                        }
                    });
                    buttonOption3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            countDownTimer.cancel();
                            buttonOption1.setClickable(false);
                            buttonOption2.setClickable(false);
                            buttonOption3.setClickable(false);
                            buttonOption4.setClickable(false);
                            if (buttonOption3.getText().toString().equals(question.getAnswer())) {
                                buttonOption3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct+=correct+5*time;
                                        buttonOption3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            } else {
                                buttonOption3.setBackgroundColor(Color.RED);
                                if (buttonOption1.getText().toString().equals(question.getAnswer())) {
                                    buttonOption1.setBackgroundColor(Color.GREEN);
                                } else if (buttonOption2.getText().toString().equals(question.getAnswer())) {
                                    buttonOption2.setBackgroundColor(Color.GREEN);
                                } else if (buttonOption4.getText().toString().equals(question.getAnswer())) {
                                    buttonOption4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        buttonOption1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            }
                        }
                    });
                    buttonOption4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            countDownTimer.cancel();
                            buttonOption1.setClickable(false);
                            buttonOption2.setClickable(false);
                            buttonOption3.setClickable(false);
                            buttonOption4.setClickable(false);
                            if (buttonOption4.getText().toString().equals(question.getAnswer())) {
                                buttonOption4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct+=correct+5*time;
                                        buttonOption4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            } else {
                                buttonOption4.setBackgroundColor(Color.RED);
                                if (buttonOption1.getText().toString().equals(question.getAnswer())) {
                                    buttonOption1.setBackgroundColor(Color.GREEN);
                                } else if (buttonOption2.getText().toString().equals(question.getAnswer())) {
                                    buttonOption2.setBackgroundColor(Color.GREEN);
                                } else if (buttonOption3.getText().toString().equals(question.getAnswer())) {
                                    buttonOption3.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        buttonOption1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        buttonOption4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            }
        }
        else{
            updateQuestions();
        }


    }
    public void updateScore(){
        Intent intent=getIntent();
        final String name=intent.getStringExtra("name");
        DatabaseReference scoreReference=FirebaseDatabase.getInstance().getReference().child("scores");
        scoreId=scoreReference.push().getKey();
        Score score= new Score(scoreId,name,String.valueOf(correct));
        scoreReference.child(scoreId).setValue(score);
        Log.d("Debug",score.scoreId);
    }



    @Override
    protected void onStart() {
        super.onStart();
        updateQuestions();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Warning")
                .setMessage("Are you sure you want to exit the quiz?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
