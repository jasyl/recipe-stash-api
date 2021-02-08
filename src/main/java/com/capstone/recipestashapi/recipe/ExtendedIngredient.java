package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendedIngredient {
    @JsonProperty("originalName")
    private String name;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("unit")
    private String unit;
//    @JsonProperty("meta")
//    private List<String> meta;

    public ExtendedIngredient() {
    }

    public ExtendedIngredient(String name, String unit, double amount) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

//    public List<String> getMeta() {
//        return meta;
//    }
//
//    public void setMeta(List<String> meta) {
//        this.meta = meta;
//    }

    @Override
    public String toString() {
        return "ExternalIngredient{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
//                ", meta=" + meta +
                '}';
    }
}
