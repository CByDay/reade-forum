package cn.bugstack.reade.forum.domain.service;

import cn.bugstack.reade.forum.domain.entity.UserEntity;


/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
public interface UserTransferService {

    /**
     * @Description: 登录
     * @Author: zhd
     * @Date: 2023/4/15
     * @param: userName
     * @return: UserEntity
     **/
    UserEntity loginUser(String userName);

    /**
     * @Description: 创建新用户
     * @Author: zhd
     * @Date: 2023/4/15
     * @param: username
     * @param: password
     * @param: email
     * @param: emailCode
     * @param: sessionId
     * @return: String
     **/
    String creatUser(String username, String password, String email, String emailCode, String sessionId);

    String retrievePasswordAA(String email, String emailCode, String sessionId);

    String retrievePasswordBB(String email, String passWord, String emailCode, String sessionId,String type);
}
