package cn.bugstack.reade.forum.application.service.actor.impl;

import cn.bugstack.reade.forum.application.converter.actor.ActorDtoConverter;
import cn.bugstack.reade.forum.application.service.actor.ActorService;
import cn.bugstack.reade.forum.application.vo.actor.ActorVo;
import cn.bugstack.reade.forum.domain.service.ActorTransferService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.application.service.actor.impl
 * @Author: zhd
 * @CreateTime: 2023-04-05  16:32
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class ActorServiceImpl implements ActorService {

    @Resource
    private ActorTransferService actorTransferService;

    @Override
    public List<ActorVo> findActorList() {
        System.out.println("进入application");
        return  ActorDtoConverter.actorDtoConverter.toActorDto( actorTransferService.queryActorEntity());
    }
}
