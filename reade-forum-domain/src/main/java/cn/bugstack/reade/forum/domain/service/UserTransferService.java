package cn.bugstack.reade.forum.domain.service;

import cn.bugstack.reade.forum.domain.entity.UserEntity;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
public interface UserTransferService {

    UserEntity loginUser(String userName);

    int creatUser(String username, String password, String email);
}
