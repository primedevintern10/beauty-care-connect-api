package com.beauty.api.controller;

import com.beauty.api.collection.User;
import com.beauty.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "getperson")
    public String getPerson(){return "test";}

    @PostMapping(value = "saveperson")
    public String save(@RequestBody User user){
        return userService.save(user);
    }
}
