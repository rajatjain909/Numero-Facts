package com.example.rsj.numerofacts.retrofact;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rsj.numerofacts.R;
import com.example.rsj.numerofacts.databinding.ActivityRandomFactCategoryBinding;

public class RandomFactCategory extends AppCompatActivity {

    ActivityRandomFactCategoryBinding mCategoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryBinding = DataBindingUtil.setContentView(
                RandomFactCategory.this, R.layout.activity_random_fact_category);

        /*Date intent*/
        mCategoryBinding.textViewRandomFactDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomFactCategory.this, FactPage.class);
                intent.putExtra("fact", "date");
                startActivity(intent);
            }
        });

        /*Mathematics intent*/
        mCategoryBinding.textViewRandomFactMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomFactCategory.this, FactPage.class);
                intent.putExtra("fact", "math");
                startActivity(intent);
            }
        });

        /*Trivia intent*/
        mCategoryBinding.textViewRandomFactTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomFactCategory.this, FactPage.class);
                intent.putExtra("fact", "trivia");
                startActivity(intent);
            }
        });

        /*Year intent*/
        mCategoryBinding.textViewRandomFactYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomFactCategory.this, FactPage.class);
                intent.putExtra("fact", "year");
                startActivity(intent);
            }
        });
    }
}
