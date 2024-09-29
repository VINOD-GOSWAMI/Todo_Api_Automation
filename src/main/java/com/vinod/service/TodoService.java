package com.vinod.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vinod.api.ApiClient;
import com.vinod.model.Todo;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
public class TodoService {
    private final ApiClient apiClient = new ApiClient();
    private final Gson gson = new Gson();

    public List<Todo> getTodosByUserId(int userId) {
        Response response = apiClient.get("/todos?userId=" + userId);
        Type todoListType = new TypeToken<List<Todo>>() {}.getType();
        return gson.fromJson(response.getBody().asString(), todoListType);
    }

    public long getCompletedTasksCount(List<Todo> todos) {
        return todos.stream().filter(Todo::isCompleted).count();
    }

    public boolean hasMoreThanHalfTasksCompleted(List<Todo> todos) {
        long completedTasks = getCompletedTasksCount(todos);
        log.info("completed Tasks: {}",completedTasks);
        return completedTasks > (todos.size() / 2);
    }
}
