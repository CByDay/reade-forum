package cn.bugstack.reade.forum.domain.service.impl;

import cn.bugstack.reade.forum.domain.entity.UserEntity;
import cn.bugstack.reade.forum.domain.repository.user.UserRepository;
import cn.bugstack.reade.forum.domain.service.UserTransferService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@Service
public class UserTransferImpl implements UserTransferService {

    @Resource
    private UserRepository userRepository;


    @Override
    public UserEntity loginUser(String userName) {
        return userRepository.loadUserByUserNameOrUserEmail(userName);
    }
}
