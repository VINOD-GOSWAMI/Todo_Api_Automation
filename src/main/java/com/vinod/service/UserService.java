package com.vinod.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vinod.api.ApiClient;
import com.vinod.model.User;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.List;

public class UserService {
    private final ApiClient apiClient = new ApiClient();
    private final Gson gson = new Gson();

    public List<User> getAllUsers() {
        Response response = apiClient.get("/users");
        Type userListType = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(response.getBody().asString(), userListType);
    }

    public boolean isFromFanCodeCity(User user) {
        double lat = Double.parseDouble(user.getAddress().getGeo().getLat());
        double lng = Double.parseDouble(user.getAddress().getGeo().getLng());
        return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
    }
}
