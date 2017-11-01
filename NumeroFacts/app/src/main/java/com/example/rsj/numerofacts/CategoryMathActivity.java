package com.example.rsj.numerofacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class CategoryMathActivity extends AppCompatActivity {

    String stringMathUrl = "http://numbersapi.com/random/math?json";
    TextView textViewMathFact, textViewNewMathFact;
    RequestQueue requestQueueMath;
    String stringMath = "";
    ProgressBar progressBarMath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_math);

        randomMathsFacts();

        textViewNewMathFact = findViewById(R.id.textview_new_math_fact);
        textViewNewMathFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomMathsFacts();
            }
        });
    }

    private void randomMathsFacts() {

        textViewMathFact = findViewById(R.id.textview_math_fact);
        progressBarMath = findViewById(R.id.progress_bar_math);

        requestQueueMath = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequestMath = new JsonObjectRequest(Request.Method.GET,
                stringMathUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObjectMath = response;
                try {
                    stringMath = jsonObjectMath.getString("text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textViewMathFact.setText(stringMath);
                progressBarMath.setVisibility(View.GONE);
                textViewMathFact.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewMathFact.setText("Can't load data. Check your connection.");
                progressBarMath.setVisibility(View.GONE);
                textViewMathFact.setVisibility(View.VISIBLE);
                Log.e("Math", "There is some problem" + error);
            }
        });
        requestQueueMath.add(jsonObjectRequestMath);
    }
}
