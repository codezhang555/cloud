package cn.itz.cloud.config;

import cn.itz.cloud.User;
import cn.itz.cloud.service.HelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PackageName: cn.itz.cloud.config
 * @ClassName: HelloServiceFallback
 * @Author: codeZhang
 * @DateTime: 2021/1/5 9:38
 * @Version 1.0
 */
@Component
@RequestMapping("/error")
public class HelloServiceFallback implements HelloService {
  @Override
  public String hello() {
    return "error";
  }

  @Override
  public String hello2(String name) {
    return "error2";
  }

  @Override
  public User addUser(User user) {
    return null;
  }

  @Override
  public void deleteUserById(Integer id) {

  }
}
