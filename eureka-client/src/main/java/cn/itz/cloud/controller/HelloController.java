package cn.itz.cloud.controller;

import cn.itz.cloud.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeZhang
 * @ProjectName cloud
 * @Package cn.itz.cloud.controller
 * @Version 1.0
 * @date 2021/1/3 16:59
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello(){
        return "hello" + port;
    }

    @GetMapping("/hello2")
    public String hello2(String name){
        return "hello" + name;
    }

    /**
     * 以key-value形式传参
     * @param user
     * @return
     */
    @PostMapping("/user1")
    public User addUser1(User user){
        return user;
    }

    /**
     * 以json形式传参
     * @param user
     * @return
     */
    @PostMapping("/user2")
    public User addUser2(@RequestBody User user){
        return user;
    }

}
