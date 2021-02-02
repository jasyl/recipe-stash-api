package com.capstone.recipestashapi.recipe;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "app_user",
        uniqueConstraints = {
            @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email; // Must be Unique

    @OneToMany(
            mappedBy = "user",
            targetEntity=Recipe.class,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Recipe> recipes = new ArrayList<>();

    public User() {
    }

    public User( String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }


    public void addRecipe(Recipe recipe) {
        if (!this.recipes.contains(recipe)) {
            this.recipes.add(recipe);
            recipe.setUser(this);
        }
    }

    public void removeRecipe(Recipe recipe) {
        if (this.recipes.contains(recipe)) {
            this.recipes.remove(recipe);
            recipe.setUser(null);
        }
    }
}
