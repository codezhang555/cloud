package cn.itz.cloud.controller;

import cn.itz.cloud.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeZhang
 * @ProjectName cloud
 * @Package cn.itz.cloud.controller
 * @Version 1.0
 * @date 2021/1/4 21:21
 */
@RestController
public class UserController {

    /**
     * 这个接口既可以处理合并之后的请求，也可以处理单个请求(单个请求的话，List集合中就只有一项数据)
     * @param ids
     * @return
     */
    @GetMapping("/user/{ids}") //假设consumer 传过来的多个id的格式是1,2,3,4
    public List<User> getUserByIds(@PathVariable String ids){
        System.out.println(ids);
        String[] split = ids.split(",");
        List<User> users = new ArrayList<>();
        for (String s : split) {
            User u = new User();
            u.setId(Integer.parseInt(s));
            users.add(u);
        }
        return users;
    }
}
