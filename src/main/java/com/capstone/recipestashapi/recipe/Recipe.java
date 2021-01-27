package com.capstone.recipestashapi.recipe;

import javax.persistence.*;

@Entity
@Table
public class Recipe {
    @Id
    @SequenceGenerator(
            name = "recipe_sequence",
            sequenceName = "recipe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_sequence"
    )
    private Long id;
    private Long externalId;
    private int readyInMinutes;
    private int servings;
    private String img;
    private String sourceUrl;
    private String instructions;

    public Recipe() {
    }

    public Recipe(Long id,
                  Long externalId,
                  int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String instructions) {
        this.id = id;
        this.externalId = externalId;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.instructions = instructions;
    }

    public Recipe(Long externalId, int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String instructions) {
        this.externalId = externalId;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.instructions = instructions;
    }

    public Recipe(int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String instructions) {
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.instructions = instructions;
    }

    public Recipe(int readyInMinutes,
                  int servings,
                  String img,
                  String instructions) {
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.instructions = instructions;
    }

    public Recipe(int readyInMinutes,
                  int servings,
                  String instructions) {
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.instructions = instructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", readyInMinutes=" + readyInMinutes +
                ", servings=" + servings +
                ", img='" + img + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
