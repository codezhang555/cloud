package cn.itz.cloud.service;

import cn.itz.cloud.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @PackageName: cn.itz.cloud.service
 * @ClassName: HelloService
 * @Author: codeZhang
 * @DateTime: 2021/1/5 8:49
 * @Version 1.0
 */
@FeignClient("provider")
public interface HelloService extends IUserService{

//  @GetMapping("/hello")
//  String hello();
//
//  @GetMapping("/hello2")
//  String hello2(@RequestParam("name") String name);
//
//  @PostMapping("/user2")
//  User addUser(@RequestBody User user);
//
//  @DeleteMapping("/user2/{id}")
//  void deleteUserById(@PathVariable Integer id);

//  @GetMapping("/user3")
//  void getUserByName(@RequestHeader("name") String name);
}
