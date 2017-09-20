package com.example.jay.stark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Jay on 20-08-2017.
 */
public class PitchSelection extends RealmObject{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("material")
    @Expose
    private String material;
    @SerializedName("greaterone")
    @Expose
    private String greaterone;
    @SerializedName("oneonefive")
    @Expose
    private String oneonefive;
    @SerializedName("onefivetwo")
    @Expose
    private String onefivetwo;
    @SerializedName("twothree")
    @Expose
    private String twothree;
    @SerializedName("threefour")
    @Expose
    private String threefour;
    @SerializedName("lessfour")
    @Expose
    private String lessfour;
    @SerializedName("tentwenty")
    @Expose
    private String tentwenty;
    @SerializedName("twentyfourty")
    @Expose
    private String twentyfourty;
    @SerializedName("fourtysixty")
    @Expose
    private String fourtysixty;
    @SerializedName("sixtyninty")
    @Expose
    private String sixtyninty;
    @SerializedName("nintyoneonezero")
    @Expose
    private String nintyoneonezero;
    @SerializedName("oneonezeroonethreezero")
    @Expose
    private String oneonezeroonethreezero;
    @SerializedName("onethreezeroonefivezero")
    @Expose
    private String onethreezeroonefivezero;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getGreaterone() {
        return greaterone;
    }

    public void setGreaterone(String greaterone) {
        this.greaterone = greaterone;
    }

    public String getOneonefive() {
        return oneonefive;
    }

    public void setOneonefive(String oneonefive) {
        this.oneonefive = oneonefive;
    }

    public String getOnefivetwo() {
        return onefivetwo;
    }

    public void setOnefivetwo(String onefivetwo) {
        this.onefivetwo = onefivetwo;
    }

    public String getTwothree() {
        return twothree;
    }

    public void setTwothree(String twothree) {
        this.twothree = twothree;
    }

    public String getThreefour() {
        return threefour;
    }

    public void setThreefour(String threefour) {
        this.threefour = threefour;
    }

    public String getLessfour() {
        return lessfour;
    }

    public void setLessfour(String lessfour) {
        this.lessfour = lessfour;
    }

    public String getTentwenty() {
        return tentwenty;
    }

    public void setTentwenty(String tentwenty) {
        this.tentwenty = tentwenty;
    }

    public String getTwentyfourty() {
        return twentyfourty;
    }

    public void setTwentyfourty(String twentyfourty) {
        this.twentyfourty = twentyfourty;
    }

    public String getFourtysixty() {
        return fourtysixty;
    }

    public void setFourtysixty(String fourtysixty) {
        this.fourtysixty = fourtysixty;
    }

    public String getSixtyninty() {
        return sixtyninty;
    }

    public void setSixtyninty(String sixtyninty) {
        this.sixtyninty = sixtyninty;
    }

    public String getNintyoneonezero() {
        return nintyoneonezero;
    }

    public void setNintyoneonezero(String nintyoneonezero) {
        this.nintyoneonezero = nintyoneonezero;
    }

    public String getOneonezeroonethreezero() {
        return oneonezeroonethreezero;
    }

    public void setOneonezeroonethreezero(String oneonezeroonethreezero) {
        this.oneonezeroonethreezero = oneonezeroonethreezero;
    }

    public String getOnethreezeroonefivezero() {
        return onethreezeroonefivezero;
    }

    public void setOnethreezeroonefivezero(String onethreezeroonefivezero) {
        this.onethreezeroonefivezero = onethreezeroonefivezero;
    }
}
