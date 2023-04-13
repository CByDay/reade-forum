package cn.bugstack.reade.forum.domain.service.impl;

import cn.bugstack.reade.forum.domain.entity.UserEntity;
import cn.bugstack.reade.forum.domain.repository.user.UserRepository;
import cn.bugstack.reade.forum.domain.service.UserTransferService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@Service
public class UserTransferImpl implements UserTransferService {

    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }

    @Resource
    private UserRepository userRepository;


    @Override
    public UserEntity loginUser(String userName) {
        return userRepository.loadUserByUserNameOrUserEmail(userName);
    }

    @Override
    public int creatUser(String username, String password, String email) {



        userRepository.creatUserRepository(username,password, email,getUUID());
        return 0;
    }
}
