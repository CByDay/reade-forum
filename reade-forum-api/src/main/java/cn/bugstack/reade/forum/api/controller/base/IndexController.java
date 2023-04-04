package cn.bugstack.reade.forum.api.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.api.controller.base
 * @Author: zhd
 * @CreateTime: 2023-04-03  23:45
 * @Description: TODO
 * @Version: 1.0
 */
//@Controller
//@RequestMapping("/index")
@RestController
//@RequestMapping("/actor")
public class IndexController {

//    @ApiOperation(value = "首页", notes = "首页", httpMethod = "GET")
    @GetMapping("/index")
    public String order()
    {

        return "启动成功";
    }

    @GetMapping("/login")
    public String register(){
        // 登录方法
        return "登录成功";
    }

    @GetMapping("/islogin")
    public String isLogin(){
        // 验证是否登录

        return "已经登录";
    }


    @GetMapping("/out")
    public String loginOut(){

        return "注销成功";
    }
}
