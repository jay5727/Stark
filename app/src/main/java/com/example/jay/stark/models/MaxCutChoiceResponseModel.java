package com.example.jay.stark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jay on 18-08-2017.
 */
public class MaxCutChoiceResponseModel
{
    @SerializedName("MaxCutChoice")
    @Expose
    private List<MaxCutChoice> maxCutChoice = null;

    public List<MaxCutChoice> getMaxCutChoice() {
        return maxCutChoice;
    }

    public void setMaxCutChoice(List<MaxCutChoice> maxCutChoice) {
        this.maxCutChoice = maxCutChoice;
    }
}
