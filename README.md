This project was bootstrapped with [Spring Initializr](https://start.spring.io/)

# RecipeStash API

Recipe Stash API is RESTful API that allows an authenticated user to add, retrieve, update, and delete recipes. 

This API is built using Spring Boot. You can find the React [front-end repo here](https://github.com/jasyl/recipe-stash)

## Set up Notes

- You'll need to acquire an API key from [Spoonacular API](https://spoonacular.com/food-api) in order to extract recipes details from web pages & parse ingredients.
- This app uses OAuth2 and with Google as the provider.
Go to [https://console.developers.google.com](https://console.developers.google.com/) to add the appropriate permissions for your app and to get your client ID and client secret.

## Installation

Make sure you have [Java](https://java.com/en/download/) installed!

Grab your favorite Java IDE, I used [IntelliJ](https://www.jetbrains.com/idea/) for this project. 

Fork my repo and clone it!

### Database - PostgreSQL

The easiest way to install PostgreSQL on Mac is via [Postgres.app](https://postgresapp.com/).

For Windows, checkout [EDB](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) and download the latest version. 

Once installed, open up the terminal and type in `psql`. This allows you to access the terminal-based front-end to PostgreSQL. Check out the [documentation](https://www.postgresql.org/docs/13/app-psql.html) for more info.

We need to create a database to hook up with spring boot. Then grant privileges to yourself and postgres. 

```
CREATE DATABASE recipe;

GRANT ALL PRIVILEGES ON DATABASE "recipe" TO <insert username>;

GRANT ALL PRIVILEGES ON DATABASE "recipe" TO postgres;
```

I named my database recipe, if you change the name, make sure to update the `applications.yaml` file. 

```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/recipe
```

At this stage, you should be able to run the program without any issue. You won't be able to interact with the backend because of the security implemented. If you'd like to interact with it, check out the [React front-end](https://github.com/jasyl/recipe-stash) I made for it!

## Usage

### Recipes

```
GET /recipes
```

List all recipes in the currently logged in user's library

```
GET /recipes/:recipeId
```

Show details for a single recipe by ID

```
POST /recipes
```

Add a new recipe to the currently logged in user's library. Requires a request body that contains the recipe JSON.

```
POST /recipes?url=https://tasty.co/recipe/ratatouille
```

Add a new recipe via URL to the currently logged in user's library. Requires a Request Paramater that contains the recipe URL. This method will make a behind the scenes request to the Spoonacular API to extract recipe details in the form JSON.

```
PUT /recipes/:recipeId
```

Update an existing recipe of the currently logged in user. Requires a request body that contains the recipe JSON.

```
PUT /recipes/:recipeId?favorite=true
```

Update whether the recipe has been favorited or unfavorited

```
DELETE /recipes/:recipeId
```

Deletes a recipe by the recipe ID

### Ingredients

```
GET /ingredients?ingredients="1/2 cup Almonds \n 1/4 cup soy sauce"
```

Parse ingredients strings into ingredient objects. This uses the Spoonacular API to parse the data. Each ingredient in the string but be separated by a new line character.

### User

For more information about this please check out this [OAuth Spring Boot tutorial from CalliCoder](https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/).

```
GET /user/me
```

Returns the currently logged in user. 

### Authentication

For more information about this please check out this [OAuth Spring Boot tutorial from CalliCoder](https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/).

```
POST /login
```

Authenticates user and logs them in.

```
POST /signup
```

Creates a new user and logs the user in. 

## Resources

To better understand the implementation of OAuth2 and security with Spring Boot for this project, please checkout this [tutorial](https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/) & [repository](https://github.com/callicoder/spring-boot-react-oauth2-social-login-demo) by Rajeev Singh at CalliCoder. 

[Introduction to Spring Boot](https://youtu.be/9SGDpanrc8U) by Amigoscode

[Spring JPA Tutorial](https://youtu.be/8SGI_XS5OPw) by Amigoscode
