package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {
    private final RestTemplate restTemplate;

    @Autowired
    public IngredientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.key}")
    private String apiKey;


    public List<Ingredient> parseIngredients(String ingredients) throws JsonProcessingException {
        final String uri = "https://api.spoonacular.com/recipes/parseIngredients" + "?apiKey=" + apiKey;

        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("ingredientList", ingredients);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);

        String result = restTemplate.postForObject(uri, r, String.class);
        ObjectMapper mapper = new ObjectMapper();

        List<ExtendedIngredient> parsedIngredients = mapper.readValue(result, new TypeReference<List<ExtendedIngredient>>(){});
        List<Ingredient> newIngredients = new ArrayList<>();
        for (ExtendedIngredient eItem : parsedIngredients) {
            Ingredient newItem = new Ingredient(eItem.getName(), eItem.getAmount(), eItem.getUnit());
            newIngredients.add(newItem);
        }
        return newIngredients;
    }




}
