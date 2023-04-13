package cn.bugstack.reade.forum.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;

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

    String validateAndRegisterUser(String username,
                                    String password,
                                    String email,
                                    String emailCode,String sessionId);

}
