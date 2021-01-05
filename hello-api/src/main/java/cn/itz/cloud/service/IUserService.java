package cn.itz.cloud.service;

import cn.itz.cloud.User;
import org.springframework.web.bind.annotation.*;

/**
 * provider和openFeign中的公共部分
 * @PackageName: cn.itz.cloud.service
 * @ClassName: IUserService
 * @Author: codeZhang
 * @DateTime: 2021/1/5 9:16
 * @Version 1.0
 */
public interface IUserService {

  @GetMapping("/hello")
  String hello();

  @GetMapping("/hello2")
  String hello2(@RequestParam("name") String name);

  @PostMapping("/user2")
  User addUser(@RequestBody User user);

  @DeleteMapping("/user2/{id}")
  void deleteUserById(@PathVariable Integer id);

}
