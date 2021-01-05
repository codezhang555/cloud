package cn.itz.cloud.controller;

import cn.itz.cloud.User;
import cn.itz.cloud.service.IUserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author CodeZhang
 * @ProjectName cloud
 * @Package cn.itz.cloud.controller
 * @Version 1.0
 * @date 2021/1/3 16:59
 */
@RestController
public class HelloController implements IUserService {

    @Value("${server.port}")
    Integer port;

    @Override
    @RateLimiter(name="rlA")
    public String hello() {
        String s = "hello:" + port;
        System.out.println(new Date());
        return s;
    }

    @Override
    public String hello2(String name) {
        System.out.println(new Date() + ">>>" + name);
        return "hello" + name;
    }

    @Override
    public User addUser(User user) {
        return user;
    }

    @Override
    public void deleteUserById(Integer id) {
        System.out.println(id);
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
//    @PostMapping("/user2")
//    public User addUser2(@RequestBody User user){
//        return user;
//    }

    /**
     * 以key-value形式传参
     * @param user
     */
    @PutMapping("/user1")
    public void updateUser1(User user){
        System.out.println(user);
    }

    /**
     * 以json形式传参
     * @param user
     */
    @PutMapping("/user2")
    public void updateUser2(@RequestBody User user){
        System.out.println(user);
    }

    /**
     * key-value形式传递参数
     * @param id
     */
    @DeleteMapping("/user1")
    public void deleteUser1(Integer id){
        System.out.println(id);
    }

    /**
     * PathVariable(参数放在路径中)
     * @param id
     */
//    @DeleteMapping("/user2/{id}")
//    public void deleteUser2(@PathVariable Integer id){
//        System.out.println(id);
//    }
}
