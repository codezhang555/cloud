package cn.itz.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
}
