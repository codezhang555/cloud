package cn.itz.cloud.config;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @PackageName: cn.itz.cloud.config
 * @ClassName: HelloCommand
 * @Author: codeZhang
 * @DateTime: 2021/1/4 15:46
 * @Version 1.0
 */
public class HelloCommand extends HystrixCommand<String> {

  RestTemplate restTemplate;
  String name;

  public HelloCommand(Setter setter,RestTemplate restTemplate) {
    super(setter);
    this.restTemplate = restTemplate;
    this.name = name;
  }

  @Override
  protected String run() throws Exception {
    return restTemplate.getForObject("http://provider/hello",String.class);
  }

  /**
   * 这个方法就是请求失败的回调
   * @return
   */
  @Override
  protected String getFallback() {
    return "error-extends" + getExecutionException().getMessage();
  }

  /**
   * 如果继承的方式使用Hystrix，只需要重写getCacheKey方法即可
   * 调用时，初始化HystrixRequestContext
   * @return
   */
  @Override
  protected String getCacheKey() {
    return name;
  }
}
