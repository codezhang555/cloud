package cn.itz.cloud.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @PackageName: cn.itz.cloud.config
 * @ClassName: PermissFilter
 * @Author: codeZhang
 * @DateTime: 2021/1/6 17:03
 * @Version 1.0
 */
@Component
public class PermissFilter extends ZuulFilter {
  /**
   * 过滤器类型，权限判断一般是pre
   * @return
   */
  @Override
  public String filterType() {
    return "pre";
  }

  /**
   * 过滤器优先级
   * @return
   */
  @Override
  public int filterOrder() {
    return 0;
  }

  /**
   * 是否过滤
   * @return
   */
  @Override
  public boolean shouldFilter() {
    return true;
  }

  /**
   * 狠心的过滤逻辑
   * @return 这个方法虽然有返回值，但是这个返回值目前无所谓
   * @throws ZuulException
   */
  @Override
  public Object run() throws ZuulException {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest(); //获取当前请求
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    if (!"zhang".equals(name) || !"123".equals(password)){
      //如果请求条件不满足的话，直接从这里给出响应
      ctx.setSendZuulResponse(false);
      ctx.setResponseStatusCode(401);
      ctx.addZuulResponseHeader("content-type","text/html;charset=utf-8");
      ctx.setResponseBody("非法访问");
    }
    return null;
  }
}
