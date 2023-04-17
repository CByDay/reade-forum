package cn.bugstack.reade.forum.infrastructure.converter.user;

import cn.bugstack.reade.forum.domain.entity.UserEntity;
import cn.bugstack.reade.forum.infrastructure.dao.UserDao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityConverter {

    UserEntityConverter userEntityConverter =  Mappers.getMapper(UserEntityConverter.class);

    /**
     * daoList to entity
     * @param emailGroupDaoList
     * @return
     */
    List<UserEntity> toEntityList(List<UserDao> userDaoList);

    /***
     * Dao to entity
     * @param emailGroupDao
     * @return
     */
    UserEntity toEntity(UserDao userDao);

}
