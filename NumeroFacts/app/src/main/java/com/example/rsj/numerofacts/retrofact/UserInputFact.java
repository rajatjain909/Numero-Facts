package com.example.rsj.numerofacts.retrofact;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.rsj.numerofacts.R;
import com.example.rsj.numerofacts.databinding.ActivityUserInputFactBinding;
import com.example.rsj.numerofacts.retrofact.interfaces.FetchFactServiceApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInputFact extends AppCompatActivity {

    ActivityUserInputFactBinding mInputFactBinding;
    String BASE_URL = "http://numbersapi.com/";
    Call<ResponseBody> mCall;
    FetchFactServiceApi mServiceApi;
    Context mContext = UserInputFact.this;

    ArrayList<String> mMonthNames;

    ArrayList<String> mDaysMonth = new ArrayList<>(Arrays.asList("Day", "01", "02", "03",
            "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"));

    ArrayList<String> mDaysThirty;
    ArrayList<String> mDaysThirtyOne;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInputFactBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_input_fact);

        mMonthNames = new ArrayList<String>(Arrays.asList("Month", "January", "February",
                "March", "April", "May", "June", "July", "August", "September", "October",
                "November", "December"));

        mInputFactBinding.radioButtonDate.setChecked(true);

        mDaysThirty = new ArrayList<>(mDaysMonth);
        mDaysThirty.add("30");

        mDaysThirtyOne = new ArrayList<>(mDaysThirty);
        mDaysThirtyOne.add("31");

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_dropdown_item, mMonthNames);
        mInputFactBinding.spinnerMonth.setAdapter(monthAdapter);
        getRadioButtonValue();

        addDaySpinner();

        /*Retrofit Object Creation*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /*Api creation*/
        mServiceApi = retrofit.create(FetchFactServiceApi.class);

        mInputFactBinding.buttonGetFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInputFactBinding.editTextUserInput.getText() == null) {
                    Toast.makeText(UserInputFact.this, "Please enter value", Toast.LENGTH_SHORT).show();
                } else {
                    onFactButtonClick();
                }
            }
        });
    }

    /*Method for Fetching new fact*/
    void getNewFact() {
        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                if (response.isSuccessful()) {
                    try {
                        s = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("Success", "onResponse: " + s);
                    mInputFactBinding.textViewFact.setText(s);
                } else {
                    Log.d("Success", "onResponse: " + s);
                    mInputFactBinding.textViewFact.setText(s);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Fail", "onFailure: " + t);
            }
        });
    }

    /*Method for clicking radio button*/
    void getRadioButtonValue() {

        /*Date Radio button*/
        mInputFactBinding.radioButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputFactBinding.inputLayoutUserInput.setVisibility(View.GONE);
                mInputFactBinding.layoutDateSpinner.setVisibility(View.VISIBLE);

                ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(mContext,
                        android.R.layout.simple_spinner_dropdown_item, mMonthNames);
                mInputFactBinding.spinnerMonth.setAdapter(monthAdapter);

                addDaySpinner();
                mInputFactBinding.radioGroupTwo.clearCheck();
            }
        });

        /*Math Radio Button*/
        mInputFactBinding.radioButtonMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputFactBinding.inputLayoutUserInput.setVisibility(View.VISIBLE);
                mInputFactBinding.layoutDateSpinner.setVisibility(View.GONE);

                mInputFactBinding.editTextUserInput.setText("");
                mInputFactBinding.editTextUserInput.setHint("Enter a number");

                mInputFactBinding.radioGroupTwo.clearCheck();


            }
        });

        /*Trivia radio button*/
        mInputFactBinding.radioButtonTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputFactBinding.inputLayoutUserInput.setVisibility(View.VISIBLE);
                mInputFactBinding.layoutDateSpinner.setVisibility(View.GONE);

                mInputFactBinding.editTextUserInput.setText("");
                mInputFactBinding.editTextUserInput.setHint("Enter number");

                mInputFactBinding.radioGroupOne.clearCheck();
            }
        });

        /*Year radio button*/
        mInputFactBinding.radioButtonYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputFactBinding.inputLayoutUserInput.setVisibility(View.VISIBLE);
                mInputFactBinding.layoutDateSpinner.setVisibility(View.GONE);

                mInputFactBinding.editTextUserInput.setText("");
                mInputFactBinding.editTextUserInput.setHint("Enter a year");

                mInputFactBinding.radioGroupOne.clearCheck();
            }
        });
    }

    /*Method for the button "GET THE FACT"*/
    void onFactButtonClick() {
        String value = mInputFactBinding.editTextUserInput.getText().toString();

        /*Date*/
        if (mInputFactBinding.radioButtonDate.isChecked()) {
            int monthValue = mInputFactBinding.spinnerMonth.getSelectedItemPosition();
            int dayValue = mInputFactBinding.spinnerDay.getSelectedItemPosition();

            if (monthValue == 0 || dayValue == 0) {
                Toast.makeText(mContext, "Month or Day not Selected", Toast.LENGTH_SHORT).show();
            } else {
                mCall = mServiceApi.getDateFact(monthValue, dayValue);
                getNewFact();
            }
        }

        /*Math*/
        if (mInputFactBinding.radioButtonMath.isChecked()) {
            mCall = mServiceApi.getMathFact(Integer.parseInt(value));
            getNewFact();
        }

        /*trivia*/
        if (mInputFactBinding.radioButtonTrivia.isChecked()) {
            mCall = mServiceApi.getTriviaFact(Integer.parseInt(value));
            getNewFact();
        }

        /*year*/
        if (mInputFactBinding.radioButtonYear.isChecked()) {
            mCall = mServiceApi.getYearFact(Integer.parseInt(value));
            getNewFact();
        }
    }

    /*Method for addition of days to daysspinner on the basis of selected month*/
    void addDaySpinner() {

        mInputFactBinding.spinnerDay.setVisibility(View.GONE);

        mInputFactBinding.spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int monthInt = mInputFactBinding.spinnerMonth.getSelectedItemPosition();
                if (monthInt == 2) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(mContext,
                            android.R.layout.simple_spinner_dropdown_item, mDaysMonth);
                    mInputFactBinding.spinnerDay.setAdapter(arrayAdapter);
                    mInputFactBinding.spinnerDay.setVisibility(View.VISIBLE);
                } else if (monthInt == 4 || monthInt == 6 || monthInt == 9 || monthInt == 11) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(mContext,
                            android.R.layout.simple_spinner_dropdown_item, mDaysThirty);
                    mInputFactBinding.spinnerDay.setAdapter(arrayAdapter);
                    mInputFactBinding.spinnerDay.setVisibility(View.VISIBLE);
                } else if (monthInt == 1 || monthInt == 3 || monthInt == 5 || monthInt == 7
                        || monthInt == 8 || monthInt == 10 || monthInt == 12) {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(mContext,
                            android.R.layout.simple_spinner_dropdown_item, mDaysThirtyOne);
                    mInputFactBinding.spinnerDay.setAdapter(arrayAdapter);
                    mInputFactBinding.spinnerDay.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}