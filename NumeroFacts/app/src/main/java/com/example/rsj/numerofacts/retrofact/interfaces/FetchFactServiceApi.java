package com.example.rsj.numerofacts.retrofact.interfaces;

import com.example.rsj.numerofacts.retrofact.objects.ResultFact;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by R on 25-11-2017.
 */

public interface FetchFactServiceApi {

    @GET("/random/math?json")
    Call<ResultFact> getRandomMathFact();

}
