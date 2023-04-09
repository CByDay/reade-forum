package cn.bugstack.reade.forum.domain.service.impl;

import cn.bugstack.reade.forum.domain.entity.ActorEntity;
import cn.bugstack.reade.forum.domain.repository.actor.ActorRepository;
import cn.bugstack.reade.forum.domain.service.ActorTransferService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 张小疯
 * @date 2023/4/7 16:56
 * @description: domain 执行所有业务实现
 */
@Service
public class ActorTransferImpl implements ActorTransferService {

    @Resource
    private ActorRepository actorRepository;


    @Override
    public List<ActorEntity> queryActorEntity() {
        return actorRepository.queryAll();
    }
}
