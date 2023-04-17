package cn.bugstack.reade.forum.infrastructure.repository;

import cn.bugstack.reade.forum.domain.entity.ActorEntity;
import cn.bugstack.reade.forum.domain.repository.actor.ActorRepository;
import cn.bugstack.reade.forum.infrastructure.converter.actor.ActorEntityConverter;
import cn.bugstack.reade.forum.infrastructure.mapper.ActorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.infrastructure.repository
 * @Author: zhd
 * @CreateTime: 2023-04-05  01:42
 * @Description: 测试数据（无实际意义）
 * @Version: 1.0
 */
@Service
public class ActorRepositoryImpl implements ActorRepository {

    @Resource
    private ActorMapper actorMapper;

    @Override
    public List<ActorEntity> queryAll() {
        return ActorEntityConverter.actorEntityConverter.toEntityList(actorMapper.selectActor());
    }
}
