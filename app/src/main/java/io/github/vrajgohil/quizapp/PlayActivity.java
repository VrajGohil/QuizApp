package io.github.vrajgohil.quizapp;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

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
    private TextView TimerView;
    private ProgressBar progressBar;
    DatabaseReference reference;
    int total=0;
    int correct=0;
    int wrong=0;
    Random random;
    int randomNumber;
    int seconds;
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
        TimerView=findViewById(R.id.textViewTimer);
        progressBar=findViewById(R.id.progressBarTimer);

        buttonOption1.setBackgroundColor(Color.parseColor("#03A9F4"));
        buttonOption2.setBackgroundColor(Color.parseColor("#03A9F4"));
        buttonOption3.setBackgroundColor(Color.parseColor("#03A9F4"));
        buttonOption4.setBackgroundColor(Color.parseColor("#03A9F4"));
        randomCheck=new ArrayList<>();
        randomCheck.clear();

        random=new Random();



    }

    private void updateQuestions() {
        randomNumber = random.nextInt(24);
        if (!randomCheck.contains(randomNumber)) {
            total++;
            randomCheck.add(randomNumber);
            progressBar.setProgress(0);
            if (total > 10) {
                Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
                intent.putExtra("total", String.valueOf(total));
                intent.putExtra("correct", String.valueOf(correct));
                intent.putExtra("wrong", String.valueOf(wrong));
                startActivity(intent);
            } else {
            reference = FirebaseDatabase.getInstance().getReference().child("questions").child(String.valueOf(randomNumber));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Questions question = dataSnapshot.getValue(Questions.class);

                    questionView.setText(question.getQuestion());
                    buttonOption1.setText(question.getOption1());
                    buttonOption2.setText(question.getOption2());
                    buttonOption3.setText(question.getOption3());
                    buttonOption4.setText(question.getOption4());

                    buttonOption1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (buttonOption1.getText().toString().equals(question.getAnswer())) {
                                buttonOption1.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        buttonOption1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            } else {
                                wrong++;
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
                                        correct++;
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
                            if (buttonOption2.getText().toString().equals(question.getAnswer())) {
                                buttonOption2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        buttonOption2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            } else {
                                wrong++;
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
                                        correct++;
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
                            if (buttonOption3.getText().toString().equals(question.getAnswer())) {
                                buttonOption3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        buttonOption3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            } else {
                                wrong++;
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
                                        correct++;
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
                            if (buttonOption4.getText().toString().equals(question.getAnswer())) {
                                buttonOption4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        buttonOption4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updateQuestions();

                                    }
                                }, 1500);
                            } else {
                                wrong++;
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
                                        correct++;
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
    public void reverseTimer(int seconds, final TextView tv){
        new CountDownTimer(seconds*1000+1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds =(int)(millisUntilFinished/1000);
                int minutes=seconds/60;
                //tv.setText(String.format("%02d",minutes)+":"+String.format("02d",seconds));
                tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                progressBar.incrementProgressBy(1);
            }

            @Override
            public void onFinish() {
                tv.setText("Completed");

                Intent intent=new Intent(PlayActivity.this,ResultActivity.class);
                intent.putExtra("total",String.valueOf(total));
                intent.putExtra("correct",String.valueOf(correct));
                intent.putExtra("wrong",String.valueOf(wrong));
                startActivity(intent);
                updateQuestions();
            }
        }.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateQuestions();
        reverseTimer(30,TimerView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
