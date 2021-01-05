package cn.itz.cloud.controller;

import cn.itz.cloud.User;
import cn.itz.cloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @PackageName: cn.itz.cloud.controller
 * @ClassName: HelloController
 * @Author: codeZhang
 * @DateTime: 2021/1/5 8:51
 * @Version 1.0
 */
@RestController
public class HelloController {

  @Autowired
  HelloService helloService;

  @GetMapping("/hello")
  public String hello() throws UnsupportedEncodingException {
    String s = helloService.hello2("zhang");
    System.out.println(s);
    User user = new User();
    user.setId(99);
    user.setName("zhangkangxu");
    user.setPassword("123");
    User u = helloService.addUser(user);
    System.out.println(u);
    helloService.deleteUserById(1);
//    helloService.getUserByName(URLEncoder.encode("zhang","UTF-8")); //放在header中的中文参数，一定要编码之后传递
    return helloService.hello();
  }
}
