package cn.bugstack.reade.forum.domain.service;

import cn.bugstack.reade.forum.domain.entity.ActorEntity;

import java.util.List;

/**
 * @author zhd
 * @date 2023/4/7
 * @description:
 */
public interface ActorTransferService {

    List<ActorEntity> queryActorEntity();
}
