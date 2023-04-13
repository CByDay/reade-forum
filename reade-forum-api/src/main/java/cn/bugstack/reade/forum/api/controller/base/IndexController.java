package cn.bugstack.reade.forum.api.controller.base;

import cn.bugstack.reade.forum.application.service.IUserService;
import cn.bugstack.reade.forum.application.service.actor.ActorService;
import cn.bugstack.reade.forum.application.vo.actor.ActorVo;
import cn.bugstack.reade.forum.application.vo.actor.UserVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.api.controller.base
 * @Author: zhd
 * @CreateTime: 2023-04-03  23:45
 * @Description:
 * @Version: 1.0
 */
//@Controller
//@RequestMapping("/index")
@RestController
//@CrossOrigin 解决 跨域问题
@CrossOrigin
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


    @Resource
    private ActorService actorService;

    @Resource
    private IUserService iUserService;


    @GetMapping("/queryAll")
    public List<ActorVo> queryAll(){
        return actorService.findActorList();
    }

    @GetMapping("/queryVO")
    public UserVO queryVO(){
        return iUserService.loginVo();
    }
}
