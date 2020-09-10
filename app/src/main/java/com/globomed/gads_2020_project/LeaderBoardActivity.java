package com.globomed.gads_2020_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class LeaderBoardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_main);
        ViewPager viewPager=findViewById(R.id.viewpager);
        TabLayout tabLayout=findViewById(R.id.tablayout);
        CustomPagerAdapter customPagerAdapter=new CustomPagerAdapter(this);
        viewPager.setAdapter(customPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        TextView Submit=findViewById(R.id.submit);
        Submit.setOnClickListener(
                view->{
                    Intent intent=new Intent(LeaderBoardActivity.this,ProjectSubmissionActivity.class);
                    startActivity(intent);
                }
        );
    }
}
