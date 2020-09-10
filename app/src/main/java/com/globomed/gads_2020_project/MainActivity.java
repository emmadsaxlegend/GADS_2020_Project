package com.globomed.gads_2020_project;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

            }
            @Override
    protected void onStart(){
        super.onStart();
                Handler handler;
                handler=new Handler(getMainLooper());
                handler.postDelayed(
                        new Runnable(){
                            @Override
                            public void run(){
                                handler.removeCallbacks(this);
                                Intent intent=new Intent(MainActivity.this,LeaderBoardActivity.class);
                                startActivity(intent);
                            }
                        }   ,2000
                );
            }
}
