package cn.itz.cloud;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class OpenFeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenFeignApplication.class, args);
  }

  /**
   * openFeign中，可以通过配置日志，来查看整个请求的调用过程。日志级别一共分为四种：
   * 1.NONE：不开启日志，默认
   * 2.BASIC：记录请求方法、URL\响应状态码、执行时间
   * 3.HEADERS：在BASIC的基础上，加载请求/响应头
   * 4.FULL:在HEADERS的基础上，再增加body以及请求元数据
   * @return
   */
  @Bean
  Logger.Level loggerLevel(){
    return Logger.Level.FULL;
  }

}
