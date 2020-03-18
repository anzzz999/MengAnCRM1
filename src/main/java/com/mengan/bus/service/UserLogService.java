package com.mengan.bus.service;

import com.mengan.bus.domain.UserLog;
import com.mengan.bus.vo.UserLogVo;
import com.mengan.sys.domain.DataGridView;

public interface UserLogService {
    DataGridView queryAllUserLog(UserLogVo userLogVo);

    void addUserLog(UserLog userLog);

    void updateUserLog(UserLog userLog);

    void deleteUserLog(Integer id);

    void deleteBatchUserLog(Integer[] ids);

    UserLog queryUserLogById(Integer id);
}
