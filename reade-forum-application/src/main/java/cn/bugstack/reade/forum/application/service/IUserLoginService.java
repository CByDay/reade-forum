package cn.bugstack.reade.forum.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author zhd
 * @date 2023/4/13
 * @description:
 */
public interface IUserLoginService extends UserDetailsService {

    /**
     * @description: 发送邮件
     * @author: zhd
     * @date: 2023/4/13 1:22
     * @param:
     * @param: url
     * @return: boolean
     **/
    String sendValidateEmail(String emailCode, String sessionId);

    /**
     * @Description: 新用户注册并进行邮箱验证
     * @Author: zhd 
     * @Date: 2023/4/15
     * @param: username
     * @param: password
     * @param: email
     * @param: emailCode
     * @param: sessionId
     * @return: String
     **/
    String validateAndRegisterUser(String username,
                                   String password,
                                   String email,
                                   String emailCode,
                                   String sessionId);

    /**
     * @Description: 修改密码
     * @Author: zhd 
     * @Date: 2023/4/15
     * @param: email
     * @param: emailCode
     * @param: sessionId
     * @return: String
     **/
    String retrievePasswordA(String email,
                            String emailCode,
                            String sessionId);

    String retrievePasswordB(String email,
                             String password,
                             String emailCode,
                             String sessionId,
                             String type);
}
