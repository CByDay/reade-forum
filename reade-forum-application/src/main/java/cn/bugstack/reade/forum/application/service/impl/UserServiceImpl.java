package cn.bugstack.reade.forum.application.service.impl;

import cn.bugstack.reade.forum.application.converter.actor.ActorDtoConverter;
import cn.bugstack.reade.forum.application.service.IUserService;
import cn.bugstack.reade.forum.application.vo.actor.UserVO;
import cn.bugstack.reade.forum.domain.service.UserTransferService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserTransferService userTransferService;

    @Override
    public UserVO loginVo() {
//        ActorDtoConverter.actorDtoConverter.toEntity(userTransferService.loginUser()
        return null;
    }
}
