package cn.bugstack.reade.forum.infrastructure.converter.actor;

import cn.bugstack.reade.forum.domain.entity.ActorEntity;
import cn.bugstack.reade.forum.infrastructure.dao.ActorDao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.infrastructure.converter.actor
 * @Author: zhd
 * @CreateTime: 2023-04-06  16:24
 * @Description: 实体对象DAO 转换 Entity
 * @Version: 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActorEntityConverter {

    ActorEntityConverter actorEntityConverter = Mappers.getMapper(ActorEntityConverter.class);

    List<ActorEntity> toEntityList(List<ActorDao> actorDaoList);

}
