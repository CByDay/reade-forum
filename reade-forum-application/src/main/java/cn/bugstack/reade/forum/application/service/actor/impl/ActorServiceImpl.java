package cn.bugstack.reade.forum.application.service.actor.impl;

import cn.bugstack.reade.forum.application.service.actor.ActorService;
import cn.bugstack.reade.forum.application.vo.ActorVo;
import cn.bugstack.reade.forum.domain.entity.ActorEntity;
import cn.bugstack.reade.forum.domain.repository.actor.ActorRepository;
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
    private ActorRepository actorRepository;

    @Override
    public List<ActorVo> findActorList() {
        List<ActorEntity> actorEntityList = null;
        try {
            System.out.println("进入actorRepository");
            actorEntityList = actorRepository.queryAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
