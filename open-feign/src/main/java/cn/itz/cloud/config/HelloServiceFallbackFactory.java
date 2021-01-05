package cn.itz.cloud.config;

import cn.itz.cloud.User;
import cn.itz.cloud.service.HelloService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义FallbackFactory来实现请求降级
 * @PackageName: cn.itz.cloud.config
 * @ClassName: HelloServiceFallbackFactory
 * @Author: codeZhang
 * @DateTime: 2021/1/5 9:44
 * @Version 1.0
 */
@Component
public class HelloServiceFallbackFactory implements FallbackFactory<HelloService> {
  @Override
  public HelloService create(Throwable throwable) {
    return new HelloService() {
      @Override
      public String hello() {
        return "error--";
      }

      @Override
      public String hello2(String name) {
        return "error----";
      }

      @Override
      public User addUser(User user) {
        return null;
      }

      @Override
      public void deleteUserById(Integer id) {

      }
    };
  }
}
