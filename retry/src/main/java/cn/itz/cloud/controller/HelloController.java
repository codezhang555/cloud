package cn.itz.cloud.controller;


import cn.itz.cloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName: cn.itz.cloud.controller
 * @ClassName: HelloController
 * @Author: codeZhang
 * @DateTime: 2021/1/5 11:17
 * @Version 1.0
 */
@RestController
public class HelloController {
  @Autowired
  HelloService helloService;

  @GetMapping("/hello")
  public String hello(){
    return helloService.hello();
  }

}
