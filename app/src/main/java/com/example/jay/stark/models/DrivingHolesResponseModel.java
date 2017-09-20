package com.example.jay.stark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jay on 18-08-2017.
 */
public class DrivingHolesResponseModel
{
    @SerializedName("DrivingHoles")
    @Expose
    private List<DrivingHole> drivingHoles = null;

    public List<DrivingHole> getDrivingHoles() {
        return drivingHoles;
    }

    public void setDrivingHoles(List<DrivingHole> drivingHoles) {
        this.drivingHoles = drivingHoles;
    }
}
