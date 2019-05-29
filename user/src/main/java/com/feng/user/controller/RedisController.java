package com.feng.user.controller;

import com.feng.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/redis")
public class RedisController {

    /**
     * 注入响应式的ReactiveRedisTemplate
     */
    private final ReactiveRedisTemplate redisTemplate;

    @Autowired
    public RedisController(ReactiveRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 测试用
     * @author liufeng
     * @date 2019年3月15日
     * @return str
     */
    @GetMapping("/hello/{msg}")
    public Mono<String> hello(@PathVariable String msg) {
        return Mono.just("hello: "+msg);
    }

    /**
     * 添加数据
     * @author liufeng
     * @date 2019年3月15日
     * @return str
     */
    @PostMapping("/add")
    public Mono<User> add(@RequestBody User user) {
        final ReactiveValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.getAndSet("user:"+user.getId(), user);
    }

    /**
     * 添加数据
     * @author liufeng
     * @date 2019年3月15日
     * @return str
     */
    @PostMapping("/addUser")
    public Mono<User> addUser(@RequestBody User user) {
        final ReactiveValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.getAndSet(user.getId(), user);
    }

    @GetMapping(value = "/user/{id}")
    public Mono<User> findCityById(@PathVariable("id") Long id) {
        String key = "user:" + id;
        ReactiveValueOperations<String, User> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 多个操作
     * @return
     */
    @PostMapping("/putAll")
    public Flux putAll() {
        Mono a = redisTemplate.opsForValue().set("a","aaa1");
        Mono b = redisTemplate.opsForValue().set("b","bbb1");
        Mono c = redisTemplate.opsForValue().set("c","ccc1");
        return Flux.just(a, c);
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    public Mono getById(@PathVariable String id) {
        return redisTemplate.opsForValue().get(id);
    }

    /**
     * 获取多个数据
     * @return
     */
    @GetMapping("/getAll")
    public Flux getAll() {
        return Flux.just("a", "b", "c")
                .flatMap(s -> redisTemplate.opsForValue().get(s));
    }

}