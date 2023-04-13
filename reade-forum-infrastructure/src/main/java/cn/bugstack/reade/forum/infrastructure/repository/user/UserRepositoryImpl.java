package cn.bugstack.reade.forum.infrastructure.repository.user;

import cn.bugstack.reade.forum.domain.entity.UserEntity;
import cn.bugstack.reade.forum.domain.repository.user.UserRepository;
import cn.bugstack.reade.forum.infrastructure.converter.user.UserEntityConverter;
import cn.bugstack.reade.forum.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhd
 * @date 2023/4/11
 * @description: user 操作
 */
@Service
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;


    @Override
    public UserEntity loadUserByUserNameOrUserEmail(String userName) {
        return UserEntityConverter.userEntityConverter.toEntity(userMapper.loginUser(userName));
    }
}
