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

public class CategoryDateActivity extends AppCompatActivity {

    String stringDateUrl = "http://numbersapi.com/random/date?json";
    TextView textViewDateFact, textViewNewDateFact;
    RequestQueue requestQueueDate;
    String stringDate = "";
    ProgressBar progressBarDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_date);

        randomDatesFacts();

        textViewNewDateFact = findViewById(R.id.textview_new_date_fact);
        textViewNewDateFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomDatesFacts();
            }
        });
    }

    private void randomDatesFacts() {

        textViewDateFact = findViewById(R.id.textview_date_fact);
        progressBarDate = findViewById(R.id.progress_bar_date);

        requestQueueDate = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequestDate = new JsonObjectRequest(Request.Method.GET,
                stringDateUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObjectDate = response;
                try {
                    stringDate = jsonObjectDate.getString("text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textViewDateFact.setText(stringDate);
                progressBarDate.setVisibility(View.GONE);
                textViewDateFact.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewDateFact.setText("Can't load data. Check your connection.");
                progressBarDate.setVisibility(View.GONE);
                textViewDateFact.setVisibility(View.VISIBLE);
                Log.e("Date", "There is some problem" + error);
            }
        });
        requestQueueDate.add(jsonObjectRequestDate);
    }
}
