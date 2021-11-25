package co.edu.unab.BmiCalc.network;

import com.google.gson.annotations.SerializedName;

public class Bmi {

    @SerializedName("bmi")
    private String bmi;

    @SerializedName("weightCategory")
    private String weightCategory;

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getWeightCategory() {
        return weightCategory;
    }

    public void setWeightCategory(String weightCategory) {
        this.weightCategory = weightCategory;
    }
}
