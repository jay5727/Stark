package com.example.jay.stark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jay on 18-08-2017.
 */
public class MaxCutChoice
{


        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("dia_mm")
        @Expose
        private String diaMm;
        @SerializedName("basic_mm")
        @Expose
        private String basicMm;
        @SerializedName("stock_mm")
        @Expose
        private String stockMm;
        @SerializedName("plus_mm")
        @Expose
        private String plusMm;
        @SerializedName("top_mm")
        @Expose
        private String topMm;
        @SerializedName("cutting_cap_mm")
        @Expose
        private String cuttingCapMm;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDiaMm() {
            return diaMm;
        }

        public void setDiaMm(String diaMm) {
            this.diaMm = diaMm;
        }

        public String getBasicMm() {
            return basicMm;
        }

        public void setBasicMm(String basicMm) {
            this.basicMm = basicMm;
        }

        public String getStockMm() {
            return stockMm;
        }

        public void setStockMm(String stockMm) {
            this.stockMm = stockMm;
        }

        public String getPlusMm() {
            return plusMm;
        }

        public void setPlusMm(String plusMm) {
            this.plusMm = plusMm;
        }

        public String getTopMm() {
            return topMm;
        }

        public void setTopMm(String topMm) {
            this.topMm = topMm;
        }

        public String getCuttingCapMm() {
            return cuttingCapMm;
        }

        public void setCuttingCapMm(String cuttingCapMm) {
            this.cuttingCapMm = cuttingCapMm;
        }
}
