package com.example.jay.stark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jay on 20-08-2017.
 */
public class HSSStandardResponseModel {
    @SerializedName("HSSStandard")
    @Expose
    private List<HSSStandard> hSSStandard = null;

    public List<HSSStandard> getHSSStandard() {
        return hSSStandard;
    }

    public void setHSSStandard(List<HSSStandard> hSSStandard) {
        this.hSSStandard = hSSStandard;
    }
}
