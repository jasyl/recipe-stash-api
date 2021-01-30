package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int readyInMinutes;
    private int servings;
    private String img;
    private String sourceUrl;
    private String title;
    private long ingredientsId;
    private String instructions;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    public Recipe() {
    }

    public Recipe(Long externalId,
                  int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String title,
                  long ingredientsId,
                  String instructions) {
        this.externalId = externalId;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.title = title;
        this.ingredientsId = ingredientsId;
        this.instructions = instructions;
    }

    public Recipe(Long externalId, int readyInMinutes, int servings, String img, String sourceUrl, String title, long ingredientsId, String instructions, User user) {
        this.externalId = externalId;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.title = title;
        this.ingredientsId = ingredientsId;
        this.instructions = instructions;
        this.user = user;
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

    public long getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(long ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (!user.getRecipes().contains(this)) {
            user.getRecipes().add(this);
        }
    }



}
