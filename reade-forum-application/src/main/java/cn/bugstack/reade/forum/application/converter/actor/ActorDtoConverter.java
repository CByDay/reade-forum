package cn.bugstack.reade.forum.application.converter.actor;

import cn.bugstack.reade.forum.application.vo.actor.ActorVo;
import cn.bugstack.reade.forum.domain.entity.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @BelongsProject: reade-forum
 * @BelongsPackage: cn.bugstack.reade.forum.application.converter.actor
 * @Author: zhd
 * @CreateTime: 2023-04-06  16:24
 * @Description:  转换实体Entity 转换为 vo
 * @Version: 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActorDtoConverter {

    ActorDtoConverter actorDtoConverter = Mappers.getMapper(ActorDtoConverter.class);

    List<ActorVo> toActorDto(List<ActorEntity> actorEntityList);


}
