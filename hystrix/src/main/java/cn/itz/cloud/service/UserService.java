package cn.itz.cloud.service;

import cn.itz.cloud.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author CodeZhang
 * @ProjectName cloud
 * @Package cn.itz.cloud.service
 * @Version 1.0
 * @date 2021/1/4 21:33
 */
@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand
    public List<User> getUserByIds(List<Integer> ids){
        User[] users = restTemplate.getForObject("http://provider/user/{1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }

    @HystrixCollapser(batchMethod = "getUserByIds",collapserProperties = {@HystrixProperty(name="timerDelayInMilliseconds",value = "200")})
    public Future<User> getUserById(Integer id){
        return null;
    }
}
