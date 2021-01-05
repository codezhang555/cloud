package cn.itz.cloud.service;

import cn.itz.cloud.User;
import cn.itz.cloud.config.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @PackageName: cn.itz.cloud.service
 * @ClassName: HelloService
 * @Author: codeZhang
 * @DateTime: 2021/1/5 8:49
 * @Version 1.0
 */
@FeignClient(value = "provider",fallback = HelloServiceFallback.class)
public interface HelloService extends IUserService{

  /**
   * openfeign继承特性：
   * 1.使用继承特性，代码简洁明了不易出错。服务端和消费端的代码统一，一改俱改，不易出错。这是优点也是缺点，
   * 这样会提高服务端和消费端的耦合度。
   * 2.参数传递，在使用了继承之后，依然不变，参数怎么传还是怎么传。
   */

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
