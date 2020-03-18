package com.mengan.sys.service;

import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.User;
import com.mengan.sys.vo.UserVo;

/**
 * 用户服务接口
 */
public interface UserService {

    User login(User user);

    DataGridView queryAllUser(UserVo userVo);

    void addUser(UserVo userVo);

    void deleteUser(Integer userid);

    void deleteBatchUser(Integer[] ids);

    void updateUser(UserVo userVo);

    void resetUserPwd(Integer userid);

    DataGridView queryUserRole(Integer userid);

    void saveUserRole(UserVo userVo);

    User queryUserWithOldPwd(UserVo userVo);
}
