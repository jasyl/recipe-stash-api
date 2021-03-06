package com.capstone.recipestashapi.recipe;

import com.capstone.recipestashapi.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Hibernate
//@Table  // Table in our database
@Table(name = "recipe", uniqueConstraints = {
        @UniqueConstraint( name = "idx_userId_srcUrl",  columnNames ={"user_id","source_url"})
})
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

    @Column(name = "favorite")
    private Boolean favorite = false;

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

    public Recipe(
                  int readyInMinutes,
                  int servings,
                  String img,
                  String sourceUrl,
                  String title,
                  List<Ingredient> ingredients,
                  String instructions) {

        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe( int readyInMinutes, int servings, String img, String sourceUrl, String title, List<Ingredient> ingredients, String instructions, User user) {
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.user = user;
    }

    public Recipe(int readyInMinutes, int servings, String img, String sourceUrl, String title, String instructions, Boolean favorite, User user, List<Ingredient> ingredients) {
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.img = img;
        this.sourceUrl = sourceUrl;
        this.title = title;
        this.instructions = instructions;
        this.favorite = favorite;
        this.user = user;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.ingredients.clear();
        this.ingredients.addAll(ingredients);
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

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
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
