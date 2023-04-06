package cn.bugstack.reade.forum.domain.repository.actor;

import cn.bugstack.reade.forum.domain.entity.ActorEntity;

import java.util.List;

public interface ActorRepository {

    List<ActorEntity> queryAll();
}
