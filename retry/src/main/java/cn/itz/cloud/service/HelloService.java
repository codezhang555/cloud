package cn.itz.cloud.service;

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
@Retry(name="retryA") //表示要使用的重试策略
public class HelloService {

  @Autowired
  RestTemplate restTemplate;

  public String hello(){
    return restTemplate.getForObject("http://localhost:8081/hello",String.class);
  }
}
