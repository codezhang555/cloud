package cn.itz.cloud.config;

import cn.itz.cloud.User;
import cn.itz.cloud.service.UserService;
import com.netflix.hystrix.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author CodeZhang
 * @ProjectName cloud
 * @Package cn.itz.cloud.config
 * @Version 1.0
 * @date 2021/1/4 21:42
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>, User,Integer> {

    private UserService userService;
    private Integer id;

    public UserCollapseCommand(UserService userService, Integer id) {
        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("UserCollapseCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(200))); //请求超过200ms就不等待了
        this.userService = userService;
        this.id = id;
    }

    /**
     * 请求参数
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
     * 请求合并
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collection) {
        List<Integer> ids = new ArrayList<>(collection.size());
        for (CollapsedRequest<User, Integer> userIntegerCollapsedRequest : collection) {
            ids.add(userIntegerCollapsedRequest.getArgument());
        }
        return new UserBatchCommand(ids,userService);
    }

    /**
     * 请求结果分发
     * @param users
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Integer>> collection) {
        int count = 0;
        for (CollapsedRequest<User, Integer> req : collection) {
            req.setResponse(users.get(count++));
        }
    }
}
