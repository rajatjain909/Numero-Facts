package com.example.rsj.numerofacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CategoryTriviaActivity extends AppCompatActivity {

    String stringTriviaUrl = "http://numbersapi.com/random/trivia?json";
    TextView textViewTriviaFact, textViewNewTriviaFact;
    RequestQueue requestQueueTrivia;
    String stringTrivia = "";
    ProgressBar progressBarTrivia;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_trivia);

        randomTriviasFacts();

        textViewNewTriviaFact = findViewById(R.id.textview_new_trivia_fact);
        textViewNewTriviaFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomTriviasFacts();
            }
        });
    }

    private void randomTriviasFacts() {

        textViewTriviaFact = findViewById(R.id.textview_trivia_fact);
        progressBarTrivia = findViewById(R.id.progress_bar_trivia);

        requestQueueTrivia = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequestTrivia = new JsonObjectRequest(Request.Method.GET,
                stringTriviaUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObjectTrivia = response;
                try {
                    stringTrivia = jsonObjectTrivia.getString("text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textViewTriviaFact.setText(stringTrivia);
                progressBarTrivia.setVisibility(View.GONE);
                textViewTriviaFact.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewTriviaFact.setText("Can't load data. Check your connection.");
                progressBarTrivia.setVisibility(View.GONE);
                textViewTriviaFact.setVisibility(View.VISIBLE);
                Log.e("Trivia", "There is some problem" + error);
            }
        });
        requestQueueTrivia.add(jsonObjectRequestTrivia);
    }
}