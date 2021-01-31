package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendedIngredient {
    @JsonProperty("name")
    private String name;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("meta")
    private List<String> meta;

    public ExtendedIngredient() {
    }

    public ExtendedIngredient(String name, String unit, double amount) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

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
