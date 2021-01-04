package cn.itz.cloud.controller;

import cn.itz.cloud.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @PackageName: cn.itz.cloud.controller
 * @ClassName: RegisterController
 * @Author: codeZhang
 * @DateTime: 2021/1/4 10:51
 * @Version 1.0
 */
@Controller
public class RegisterController {

  /**
   * 这里的post接口，响应一定是302，否则postForLocation无效
   * 重定向地址，一定要写成绝对路径，不要写相对路径，否则调用会出现问题
   * @param user
   * @return
   */
  @PostMapping("/register")
  public String register(User user){
    return "redirect:http://provider/loginPage?name="+user.getName();
  }

  @GetMapping("/loginPage")
  @ResponseBody
  public String loginPage(String name){
    return "loginPage" + name;
  }
}
