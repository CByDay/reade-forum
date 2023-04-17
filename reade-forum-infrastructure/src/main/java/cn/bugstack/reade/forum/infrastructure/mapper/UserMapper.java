package cn.bugstack.reade.forum.infrastructure.mapper;

import cn.bugstack.reade.forum.infrastructure.dao.UserDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhd
 * @date 2023/4/11
 * @description:
 */
@Mapper
public interface UserMapper {

    UserDao loginUser(String userName);

    int creatUserMap(String username, String password, String email, String userId);

    int changePassword( String email,String password);
}
