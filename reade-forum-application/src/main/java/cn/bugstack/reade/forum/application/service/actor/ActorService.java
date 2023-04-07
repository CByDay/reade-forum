package cn.bugstack.reade.forum.application.service.actor;


import cn.bugstack.reade.forum.application.vo.actor.ActorVo;

import java.util.List;

public interface ActorService {

    List<ActorVo> findActorList();
}
