package com.example.rsj.numerofacts.retrofact.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by R on 25-11-2017.
 */

public class ResultFact {
    @SerializedName("text")
    @Expose
    private String mText;

    @SerializedName("number")
    @Expose
    private int mNumber;

    @SerializedName("found")
    @Expose
    private Boolean mFound;

    @SerializedName("type")
    @Expose
    private String mType;


    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public Boolean getFound() {
        return mFound;
    }

    public void setFound(Boolean found) {
        mFound = found;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
