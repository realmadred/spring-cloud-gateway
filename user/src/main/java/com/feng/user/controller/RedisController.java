package com.feng.user.controller;

import com.feng.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
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
    public Mono<Boolean> add(@RequestBody User user) {
        return redisTemplate.opsForValue().set(user.getId(), user);
    }


    /**
     * 多个操作
     * @return
     */
    @PostMapping("/putAll")
    public Flux putAll() {
        Mono a = redisTemplate.opsForValue().set("a","aaa");
        Mono b = redisTemplate.opsForValue().set("b","bbb");
        Mono c = redisTemplate.opsForValue().set("c","ccc");
        a.subscribe(System.out::println);//这里需要消费才行。否则无法真正操作。
        b.subscribe(System.out::println);
        c.subscribe(System.out::println);
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