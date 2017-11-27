package com.example.rsj.numerofacts.retrofact.interfaces;

import com.example.rsj.numerofacts.retrofact.objects.ResultFact;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by R on 25-11-2017.
 */

public interface FetchFactServiceApi {

    @GET("/random/date?json")
    Call<ResultFact> getRandomDateFact();

    @GET("/random/math?json")
    Call<ResultFact> getRandomMathFact();

    @GET("/random/trivia?json")
    Call<ResultFact> getRandomTriviaFact();

    @GET("/random/year?json")
    Call<ResultFact> getRandomYearFact();

    @GET("/{number}/math")
    Call<ResponseBody> getMathFact(@Path("number") int number);

}
