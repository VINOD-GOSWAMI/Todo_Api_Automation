package com.vinod;


import com.vinod.model.Todo;
import com.vinod.model.User;
import com.vinod.service.TodoService;
import com.vinod.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class FanCodeUserTodoTest extends BaseTest{

    private final UserService userService = new UserService();
    private final TodoService todoService = new TodoService();

    @Test
    public void testFanCodeUsersTodosCompletion() {
        List<User> users = userService.getAllUsers();
        log.info("all user details {}",users);
        for (User user : users) {
            if (userService.isFromFanCodeCity(user)) {
                log.info("user details {}",user);
                List<Todo> todos = todoService.getTodosByUserId(user.getId());
                log.info("todos details based on user details {}",todos);
                Assert.assertTrue( todoService.hasMoreThanHalfTasksCompleted(todos),"User " + user.getName() + " has not completed more than 50% of tasks");
            }
        }
    }

}
