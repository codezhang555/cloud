package cn.itz.cloud.config;

import cn.itz.cloud.User;
import cn.itz.cloud.service.UserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.List;

/**
 * @author CodeZhang
 * @ProjectName cloud
 * @Package cn.itz.cloud.config
 * @Version 1.0
 * @date 2021/1/4 21:38
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {

    private List<Integer> ids;
    private UserService userService;

    public UserBatchCommand(List<Integer> ids, UserService userService) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("batchCommand")).andCommandKey(HystrixCommandKey.Factory.asKey("batchKey")));
        this.ids = ids;
        this.userService = userService;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.getUserByIds(ids);
    }
}
