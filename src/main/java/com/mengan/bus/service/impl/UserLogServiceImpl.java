package com.mengan.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.bus.domain.UserLog;
import com.mengan.bus.mapper.UserLogMapper;
import com.mengan.bus.service.UserLogService;
import com.mengan.bus.vo.UserLogVo;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    UserLogMapper userLogMapper;

    @Override
    public DataGridView queryAllUserLog(UserLogVo userLogVo) {
        Page<Object> page= PageHelper.startPage(userLogVo.getPage(), userLogVo.getLimit());
        List<UserLog> data = this.userLogMapper.queryAllUserLog(userLogVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addUserLog(UserLog userLog) {
        userLogMapper.insertSelective(userLog);
    }

    @Override
    public void updateUserLog(UserLog userLog) {
        userLogMapper.updateByPrimaryKeySelective(userLog);
    }

    @Override
    public void deleteUserLog(Integer id) {
        userLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchUserLog(Integer[] ids) {
        for (Integer id : ids) {
            deleteUserLog(id);
        }
    }

    @Override
    public UserLog queryUserLogById(Integer id) {
        return userLogMapper.selectByPrimaryKey(id);
    }
}
