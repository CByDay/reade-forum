package cn.bugstack.reade.forum.infrastructure.mapper;

import cn.bugstack.reade.forum.infrastructure.domain.ActorDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper

public interface ActorMapper {

    List<ActorDao> selectActor();
}
