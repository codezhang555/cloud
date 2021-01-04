package cn.itz.cloud.service;

import cn.itz.cloud.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

    public List<User> getUserByIds(List<Integer> ids){
        User[] users = restTemplate.getForObject("http://provider/user/{1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }
}
