package com.mengan.bus.mapper;

import com.mengan.bus.domain.UserLog;
import com.mengan.bus.vo.UserLogVo;

import java.util.List;

public interface UserLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);

    /**
     * 查询用户记录
     */
    List<UserLog> queryAllUserLog(UserLogVo userLogVo);
}