package com.example.rsj.numerofacts.retrofact;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.rsj.numerofacts.R;
import com.example.rsj.numerofacts.databinding.ActivityFactPageBinding;
import com.example.rsj.numerofacts.retrofact.interfaces.FetchFactServiceApi;
import com.example.rsj.numerofacts.retrofact.objects.ResultFact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactPage extends AppCompatActivity {

    ActivityFactPageBinding mFactPageBinding;
    String BASE_URL = "http://numbersapi.com";
    ResultFact mResultFact = new ResultFact();
    FetchFactServiceApi mServiceApi;
    Call<ResultFact> mResultFactCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFactPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_fact_page);

        /*retrofit object creation*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /* Api call */
        mServiceApi = retrofit.create(FetchFactServiceApi.class);

        mResultFactCall = apiCall();

        forNewFact(mResultFactCall);

        mFactPageBinding.buttomNewFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultFactCall = apiCall();
                forNewFact(mResultFactCall);
            }
        });

    }

    Call<ResultFact> apiCall() {
        return mServiceApi.getRandomMathFact();
    }

    void forNewFact(Call<ResultFact> mResultFactCall) {
        mResultFactCall.enqueue(new Callback<ResultFact>() {
            @Override
            public void onResponse(Call<ResultFact> call, Response<ResultFact> response) {
                if (response.isSuccessful()) {
                    mResultFact = response.body();
                    Log.d("Success", "onResponse: " + mResultFact.getText());
                    mFactPageBinding.textViewFact.setText(mResultFact.getText());
                } else {
                    Log.d("Foul", "onResponse: " + mResultFact.getText());
                    mFactPageBinding.textViewFact.setText("Foul");
                }
            }

            @Override
            public void onFailure(Call<ResultFact> call, Throwable t) {
                Log.d("Fail", "onFailure: " + t);
                mFactPageBinding.textViewFact.setText("Fail");
            }
        });
    }
}