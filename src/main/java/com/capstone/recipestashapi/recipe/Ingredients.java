package com.capstone.recipestashapi.recipe;

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
    private String ingredient;
    private String qty;

    public Ingredients() {
    }

    public Ingredients(long id, String ingredient, String qty) {
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

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
