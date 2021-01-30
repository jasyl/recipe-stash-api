package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "ready_in_minutes")
    private int readyInMinutes;

    @Column(name = "servings")
    private int servings;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String img;

    @Column(name = "source_url", columnDefinition = "TEXT")
    private String sourceUrl;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @OneToMany(
            mappedBy = "recipe",
            targetEntity = Ingredient.class,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(Long externalId,
                  int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String title,
                  List<Ingredient> ingredients,
                  String instructions) {
        this.externalId = externalId;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(Long externalId, int readyInMinutes, int servings, String img, String sourceUrl, String title, List<Ingredient> ingredients, String instructions, User user) {
        this.externalId = externalId;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.title = title;
        this.ingredients = ingredients;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void addIngredientItem(Ingredient item) {
        item.setRecipe(this);
        ingredients.add(item);
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

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", readyInMinutes=" + readyInMinutes +
                ", servings=" + servings +
                ", img='" + img + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", title='" + title + '\'' +
                ", instructions='" + instructions + '\'' +
                ", user=" + user +
                ", ingredients=" + ingredients +
                '}';
    }
}
