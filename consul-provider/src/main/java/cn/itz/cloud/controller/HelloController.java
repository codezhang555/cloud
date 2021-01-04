package cn.itz.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName: cn.itz.cloud.controller
 * @ClassName: HelloController
 * @Author: codeZhang
 * @DateTime: 2021/1/4 14:40
 * @Version 1.0
 */
@RestController
public class HelloController {

  @Value("${server.port}")
  Integer port;

  @GetMapping("/hello")
  public String hello(){
    return "hello>>"+port;
  }
}
