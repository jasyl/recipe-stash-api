package com.capstone.recipestashapi.model;

import com.capstone.recipestashapi.recipe.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Validated
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


//
//    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
//    private String firstName;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Email
    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email; // Must be Unique

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;
//
    @NotNull
    @Column(name = "auth_provider")
    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.local; // todo need to remove equal later

    private String providerId;

    @OneToMany(
            mappedBy = "user",
            targetEntity= Recipe.class,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Recipe> recipes = new ArrayList<>();

    public User() {
    }

    public User( String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
