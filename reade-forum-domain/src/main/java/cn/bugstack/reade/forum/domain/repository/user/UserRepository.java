package cn.bugstack.reade.forum.domain.repository.user;

import cn.bugstack.reade.forum.domain.entity.UserEntity;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
public interface UserRepository {

    UserEntity loadUserByUserNameOrUserEmail(String userName);
}
