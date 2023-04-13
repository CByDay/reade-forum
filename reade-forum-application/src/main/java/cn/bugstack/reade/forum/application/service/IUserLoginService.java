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
    boolean sendValidateEmail(String emailCode,String sessionId);
}
