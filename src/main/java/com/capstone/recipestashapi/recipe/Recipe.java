package com.capstone.recipestashapi.recipe;

import javax.persistence.*;

@Entity // Hibernate
@Table  // Table in our database
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
    private String title;
    private int readyInMinutes;
    private int servings;
    private String img;
    private String sourceUrl;
    private String ingredients;
    private String instructions;

    public Recipe() {
    }

    public Recipe(Long id,
                  Long externalId,
                  String title,
                  int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String ingredients,
                  String instructions) {
        this.id = id;
        this.externalId = externalId;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(Long externalId,
                  String title,
                  int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String ingredients,
                  String instructions) {
        this.externalId = externalId;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(String title,
                  int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String ingredients,
                  String instructions) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(String title,
                  int readyInMinutes,
                  int servings,
                  String img,
                  String ingredients,
                  String instructions) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(String title,
                  int readyInMinutes,
                  int servings,
                  String ingredients,
                  String instructions) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(Long id, String title, int readyInMinutes, int servings, String ingredients, String instructions) {
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.ingredients = ingredients;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {

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
