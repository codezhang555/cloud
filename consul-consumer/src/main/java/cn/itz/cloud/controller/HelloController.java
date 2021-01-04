package cn.itz.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @PackageName: cn.itz.cloud.controller
 * @ClassName: HelloController
 * @Author: codeZhang
 * @DateTime: 2021/1/4 15:25
 * @Version 1.0
 */
@RestController
public class HelloController {

  @Autowired
  LoadBalancerClient loadBalancerClient;
  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/hello")
  public void hello(){
    ServiceInstance choose = loadBalancerClient.choose("consul-provider");
    System.out.println("服务地址："+choose.getUri());
    System.out.println("服务名称："+choose.getServiceId());
    String s = restTemplate.getForObject(choose.getUri() + "/hello", String.class);
    System.out.println(s);
  }

}
