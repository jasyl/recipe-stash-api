package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Ingredient {

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

    @Column(name = "quantity")
    private double qty;

    @Column(name = "unit", columnDefinition = "TEXT")
    private String unit;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String ingredient, double qty) {
        this.ingredient = ingredient;
        this.qty = qty;
    }

    public Ingredient(String ingredient, double qty, String unit) {
        this.ingredient = ingredient;
        this.qty = qty;
        this.unit = unit;
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

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonIgnore
    public Recipe getRecipe() {
        return recipe;
    }

    @JsonIgnore
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        if (!recipe.getIngredients().contains(this)) {
            recipe.getIngredients().add(this);
        }
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredient='" + ingredient + '\'' +
                ", qty='" + qty + '\'' +
//                ", recipe=" + recipe +
                '}';
    }
}
