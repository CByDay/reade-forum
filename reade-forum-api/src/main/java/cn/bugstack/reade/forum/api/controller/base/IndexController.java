package cn.bugstack.reade.forum.api.controller.base;

import cn.bugstack.reade.forum.application.service.actor.ActorService;
import cn.bugstack.reade.forum.application.vo.ActorVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private ActorService actorService;


    @GetMapping("/queryAll")
    public List<ActorVo> queryAll(){
        try {
            System.out.println("控制层进入");
            List<ActorVo> actorVoList = actorService.findActorList();
            System.out.println("执行完毕");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
