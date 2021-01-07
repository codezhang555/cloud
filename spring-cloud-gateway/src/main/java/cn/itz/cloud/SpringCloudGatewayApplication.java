package cn.itz.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Spring Cloud Gateway
 * 特点
 *  - 限流
 *  - 路径重写
 *  - 动态路由
 *  - 集成spring cloud DiscoveryClient
 *  - 集成Hystrix断路器
 *  和zuul对比：
 *  - Zuul是Netflix公司的开源产品，Spring Cloud Gateway是spring家族中的产品，
 *    可以和spring家族中的其他组件更好的融合
 *  - Zuul不支持长连接，例如websocket
 *  - Spring Cloud Gateway支持限流
 *  - Spring Cloud Gateway基于Netty开发，实现了异步和非阻塞，占用资源更小，性能强于Zuul
 *  Spring Cloud Gateway支持两种不同的写法
 *  - 编码式
 *  - yml配置
 */
@SpringBootApplication
public class SpringCloudGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudGatewayApplication.class, args);
  }

  /**
   * 1.提供RouteLocator这个Bean，就可以实现请求转发。
   * 2.properties配置
   * 3.yml配置
   */
//  @Bean
//  RouteLocator routeLocator(RouteLocatorBuilder builder){
//    return builder.routes()
//        .route("zhang_route",r -> r.path("/get").uri("http://httpbin.org"))
//        .build();
//  }

}
