package com.beauty.api.controller;

import com.beauty.api.collection.User;
import com.beauty.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "saveperson")
    private String save(@RequestBody User user){
        return userService.save(user);
    }

    public List<User> getUserStartWith(@RequestParam("name") String name){
        return userService.getPersonStartWith(name);
    }
}
