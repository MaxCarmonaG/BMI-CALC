package co.edu.unab.BmiCalc.model;

import java.io.Serializable;
import java.util.HashMap;

public class Record implements Serializable {
    private String id;
    private String date;
    private float weight;
    private float height;
    private String bmi;
    private String recommendation;

    public Record() {
    }

    public Record(String date, float weight, float height, String bmi, String recommendation) {
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.recommendation = recommendation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public HashMap<String, Object> getMap() {
        HashMap<String, Object> recordMap = new HashMap<>();
        recordMap.put("id", getId());
        recordMap.put("date", getDate());
        recordMap.put("weight", getWeight());
        recordMap.put("height", getHeight());
        recordMap.put("bmi", getBmi());
        recordMap.put("recommendation", getRecommendation());

        return recordMap;
    }
}
