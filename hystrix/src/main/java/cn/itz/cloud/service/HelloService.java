package cn.itz.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @PackageName: cn.itz.cloud.service
 * @ClassName: HelloService
 * @Author: codeZhang
 * @DateTime: 2021/1/4 15:41
 * @Version 1.0
 */
@Service
public class HelloService {

  @Autowired
  RestTemplate restTemplate;

  /**
   * 在这个方法中，我们将发起一个远程调用，调用provider中提供的/hello接口
   *
   * 但是，这个调用可能会失败
   *
   * 我们在这个方法上添加@HystrixCommand注解，配置fallbackMethod属性，这个属性表示该方法调用失败时的临时替代方法
   *
   * @return
   */
  @HystrixCommand(fallbackMethod = "error")
  public String hello(){
    return restTemplate.getForObject("http://provider/hello",String.class);
  }

  /**
   * 这个方法名字要和fallbackMethod一致
   * 方法返回值也要和对应的方法一致
   * @return
   */
  public String error(Throwable t){
    return "error" + t.getMessage();
  }
  public String error2(String name){ return "error"; }

  /**
   * 请求异步调用
   * @return
   */
  @HystrixCommand(fallbackMethod = "error")
  public Future<String> hello2(){
    return new AsyncResult<String>() {
      @Override
      public String invoke() {
        return restTemplate.getForObject("http://provider/hello",String.class);
      }
    };
  }

  @HystrixCommand(fallbackMethod = "error2")
  @CacheResult  //这个注解表示该方法的请求结果会被缓存起来，默认情况下，缓存的key就是方法的参数，缓存的value就是方法的返回值
  public String hello3(String name){
    return restTemplate.getForObject("http://provider/hello2?name={1}",String.class,name);
  }

  /**
   * 默认情况下，缓存的key就是所调用的方法的参数，如果参数有多个，就是多个参数组合起来作为缓存的key，例如如下方法
   */
//  @HystrixCommand(fallbackMethod = "error2")
//  @CacheResult  //这个注解表示该方法的请求结果会被缓存起来，默认情况下，缓存的key就是方法的参数，缓存的value就是方法的返回值
//  public String hello3(String name, Integer age){
//    return restTemplate.getForObject("http://provider/hello2?name={1}",String.class,name);
//  }

  /**
   * 如果有多个参数，但是又只想使用其中一个作为缓存的key,可以通过@Cachekey注解来解决
   *
   * 虽然有两个参数，但是缓存以name为准。两次请求中，只要name一样，即使age不一样，第二次请求也可以使用第一次请求缓存的结果
   */
//  @HystrixCommand(fallbackMethod = "error2")
//  @CacheResult  //这个注解表示该方法的请求结果会被缓存起来，默认情况下，缓存的key就是方法的参数，缓存的value就是方法的返回值
//  public String hello3(@CacheKey String name, Integer age){
//    return restTemplate.getForObject("http://provider/hello2?name={1}",String.class,name);
//  }

  /**
   * 在做数据缓存时，如果有一个数据删除的方法，一般除了删数据库中的数据，还希望删除缓存中的数据，这个时候@CacheRemove()。
   * 在使用时，必须制定commandKey属性，commandKey其实就是缓存方法的名字，制定了commandKey，@CacheRemove才能找到数据缓存
   * 在哪里，进而才能删除掉数据。
   */
  @HystrixCommand
  @CacheRemove(commandKey = "hello3")
  public String deleteUserByName(String name){
    return null;
  }
}
