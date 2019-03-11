package com.feng.user.controller;

import com.feng.common.entity.PageParam;
import com.feng.common.result.Result;
import com.feng.user.entity.User;
import com.feng.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        return Result.newSuccessInstance(userService.save(user));
    }

    @GetMapping("/{id}")
    public Result byId(@PathVariable Long id) {
        return Result.newSuccessInstance(userService.findOne(id));
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return Result.newSuccessInstance();
    }

    @GetMapping("/byName/{username}")
    public Result byName(@PathVariable String username) {
        return Result.newSuccessInstance(userService.findByUsername(username));
    }

    @GetMapping("/byNamePage/{username}")
    public Result byNamePage(@PathVariable String username,@ModelAttribute PageParam pageParam) {
        return Result.newSuccessInstance(userService.findByUsername(username,pageParam));
    }

    @GetMapping("/all")
    public Result all() {
        return Result.newSuccessInstance(userService.findAll());
    }

    @GetMapping("/count")
    public Result count() {
        return Result.newSuccessInstance(userService.count());
    }
}
