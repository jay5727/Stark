package com.example.jay.stark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Jay on 18-08-2017.
 */
public class DrivingHole extends RealmObject
{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("bore")
    @Expose
    private String bore;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("Dholes")
    @Expose
    private String dholes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBore() {
        return bore;
    }

    public void setBore(String bore) {
        this.bore = bore;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDholes() {
        return dholes;
    }

    public void setDholes(String dholes) {
        this.dholes = dholes;
    }
}