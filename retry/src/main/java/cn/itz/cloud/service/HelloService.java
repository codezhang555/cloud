package cn.itz.cloud.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @PackageName: cn.itz.cloud.service
 * @ClassName: HelloService
 * @Author: codeZhang
 * @DateTime: 2021/1/5 11:15
 * @Version 1.0
 */
@Service
//@Retry(name="retryA") //表示要使用的重试策略
@CircuitBreaker(name = "cbA",fallbackMethod = "error") //@CircuitBreaker注解中的name属性用来指定circuitBreaker配置，fallbackMethod方法用来指定服务降级的方法，服务降级方法中，要添加异常参数
public class HelloService {

  @Autowired
  RestTemplate restTemplate;

  public String hello(){
    for (int i = 0; i < 5; i++) {
       restTemplate.getForObject("http://localhost:8085/hello",String.class);
    }
    return "success";
  }

  public String error(Throwable t){
    return "error";
  }
}
