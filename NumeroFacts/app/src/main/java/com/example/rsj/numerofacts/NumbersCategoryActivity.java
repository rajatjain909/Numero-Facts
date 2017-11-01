package com.example.rsj.numerofacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NumbersCategoryActivity extends AppCompatActivity {

    TextView textViewCategoryTrivia, textViewCategoryMath, textViewCategoryDate, textViewCategoryYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_category);

        /*To date category*/
        textViewCategoryDate = findViewById(R.id.textview_category_date);
        textViewCategoryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDate = new Intent(NumbersCategoryActivity.this, CategoryDateActivity.class);
                startActivity(intentDate);
            }
        });

        /*To math category*/
        textViewCategoryMath = findViewById(R.id.textview_category_math);
        textViewCategoryMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMath = new Intent(NumbersCategoryActivity.this, CategoryMathActivity.class);
                startActivity(intentMath);
            }
        });

        /*To trivia category*/
        textViewCategoryTrivia = findViewById(R.id.textview_category_trivia);
        textViewCategoryTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTrivia = new Intent(NumbersCategoryActivity.this, CategoryTriviaActivity.class);
                startActivity(intentTrivia);
            }
        });

        /*To year category*/
        textViewCategoryYear = findViewById(R.id.textview_category_year);
        textViewCategoryYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentYearIntent = new Intent(NumbersCategoryActivity.this, CategoryYearActivity.class);
                startActivity(intentYearIntent);
            }
        });
    }
}
