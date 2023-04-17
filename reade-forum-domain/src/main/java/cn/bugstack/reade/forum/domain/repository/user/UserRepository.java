package cn.bugstack.reade.forum.domain.repository.user;

import cn.bugstack.reade.forum.domain.entity.UserEntity;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
public interface UserRepository {

    UserEntity loadUserByUserNameOrUserEmail(String userName);

    int creatUserRepository(String username, String password, String email, String userId);

    int changePassword(String username, String email, String password);
}
