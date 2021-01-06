package cn.itz.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaProviderApplication.class, args);
  }

}
