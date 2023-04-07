package cn.bugstack.reade.forum.infrastructure.mapper;

import cn.bugstack.reade.forum.infrastructure.dao.ActorDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhd
 * @date 2023/4/7
 * @description:
 */
@Mapper
public interface ActorMapper {

    List<ActorDao> selectActor();
}
