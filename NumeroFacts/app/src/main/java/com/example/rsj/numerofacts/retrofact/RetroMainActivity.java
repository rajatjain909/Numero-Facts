package com.example.rsj.numerofacts.retrofact;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rsj.numerofacts.R;
import com.example.rsj.numerofacts.databinding.ActivityRetroMainBinding;

public class RetroMainActivity extends AppCompatActivity {

    ActivityRetroMainBinding mRetroMainBinding;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRetroMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_retro_main);

        /* For go to Random fact category page*/
        mRetroMainBinding.textViewFactRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetroMainActivity.this, RandomFactCategory.class));
            }
        });

        mRetroMainBinding.textViewFactUserInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetroMainActivity.this, UserInputFact.class));
            }
        });
    }
}
