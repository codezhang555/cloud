package cn.itz.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 微服务由于服务数量多，所以出故障的概率很大，
 * 官方建议监控工具使用Micrometer
 */
@SpringBootApplication
public class MicrometerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrometerApplication.class, args);
    }

}
