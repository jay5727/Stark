package com.example.jay.stark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jay on 20-08-2017.
 */
public class PitchSelectionResponseModel {

    @SerializedName("PitchSelection")
    @Expose
    private List<PitchSelection> pitchSelection = null;

    public List<PitchSelection> getPitchSelection() {
        return pitchSelection;
    }

    public void setPitchSelection(List<PitchSelection> pitchSelection) {
        this.pitchSelection = pitchSelection;
    }

}
