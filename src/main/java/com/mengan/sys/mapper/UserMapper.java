package com.mengan.sys.mapper;

import com.mengan.sys.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 用户登录
     */
    User login(User user);

    void login();

    List<User> queryAllUser(User user);

    /**
     * 保存角色和用户的关系 sys_role_user
     */
    void insertUserRole(@Param("uid")Integer uid, @Param("rid")Integer rid);

}