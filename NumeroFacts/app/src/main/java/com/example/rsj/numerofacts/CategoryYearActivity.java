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

public class CategoryYearActivity extends AppCompatActivity {
    
    String stringYearUrl = "http://numbersapi.com/random/year?json";
    TextView textViewYearFact, textViewNewYearFact;
    RequestQueue requestQueueYear;
    String stringYear = "";
    ProgressBar progressBarYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_year);

        randomYearsFacts();

        textViewNewYearFact = findViewById(R.id.textview_new_year_fact);
        textViewNewYearFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomYearsFacts();
            }
        });
    }

    private void randomYearsFacts() {

        textViewYearFact = findViewById(R.id.textview_year_fact);
        progressBarYear = findViewById(R.id.progress_bar_year);

        requestQueueYear = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequestYear = new JsonObjectRequest(Request.Method.GET,
                stringYearUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObjectYear = response;
                try {
                    stringYear = jsonObjectYear.getString("text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textViewYearFact.setText(stringYear);
                progressBarYear.setVisibility(View.GONE);
                textViewYearFact.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewYearFact.setText("Can't load data. Check your connection.");
                progressBarYear.setVisibility(View.GONE);
                textViewYearFact.setVisibility(View.VISIBLE);
                Log.e("Year", "There is some problem" + error);
            }
        });
        requestQueueYear.add(jsonObjectRequestYear);
    }
}
