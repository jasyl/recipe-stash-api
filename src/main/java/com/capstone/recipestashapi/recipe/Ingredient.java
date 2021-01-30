package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Ingredients {

    @Id
    @SequenceGenerator(
            name = "ingredients_sequence",
            sequenceName = "ingredients_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ingredient_sequence"
    )


    private long id;

    @Column(name = "ingredient", columnDefinition = "TEXT")
    private String ingredient;

    @Column(name = "quantity", columnDefinition = "TEXT")
    private String qty;

    @JsonIgnore
    @ManyToOne(targetEntity = Recipe.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable=false)
    private Recipe recipe;

    public Ingredients() {
    }

    public Ingredients(String ingredient, String qty) {
        this.id = id;
        this.ingredient = ingredient;
        this.qty = qty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    private void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
