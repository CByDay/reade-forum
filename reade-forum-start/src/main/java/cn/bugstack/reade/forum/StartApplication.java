package cn.bugstack.reade.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: org.example
 * @Author: zhd
 * @CreateTime: 2023-04-02  22:59
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootApplication
//扫描 所有 cn.bugstack.reade.forum 路径下的类装配到spring容器中
//@ComponentScan({"cn.bugstack.reade.forum.**"}) //这是 controller
//@MapperScan("cn.bugstack.reade.forum.*.mapper")    //这是 mapper
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class,args);
    }
}
