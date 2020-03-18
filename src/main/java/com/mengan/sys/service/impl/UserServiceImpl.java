package com.mengan.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.sys.constant.SysConstant;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.Role;
import com.mengan.sys.domain.User;
import com.mengan.sys.mapper.RoleMapper;
import com.mengan.sys.mapper.UserMapper;
import com.mengan.sys.service.UserService;
import com.mengan.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public User login(User user) {
        //明文123456
        //生成密文MD5加密
        //DigestUtils Spring自带
        String pwd = DigestUtils.md5DigestAsHex(user.getPwd().getBytes());
        user.setPwd(pwd);
        return userMapper.login(user);
    }

    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        //使用分页助手
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<User> data = userMapper.queryAllUser(userVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addUser(UserVo userVo) {
        //DigestUtils Spring自带
        String MD5pwd = DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAnULT_PWD.getBytes());
        userVo.setPwd(MD5pwd);
        userVo.setType(SysConstant.USER_TYPE_NORMAL);
        userMapper.insertSelective(userVo);

    }

    @Override
    public void deleteUser(Integer userid) {
        // 删除用户
        userMapper.deleteByPrimaryKey(userid);
        // 根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(userid);

    }

    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteUser(id);
        }
    }

    @Override
    public void updateUser(UserVo userVo) {
        if (userVo.getPwd()!=null) {
            userVo.setPwd(DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes()));
        }
        userMapper.updateByPrimaryKeySelective(userVo);
    }

    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setUserid(userid);
        String MD5pwd = DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAnULT_PWD.getBytes());
        user.setPwd(MD5pwd);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public DataGridView queryUserRole(Integer userid) {
        //1,查询所有可用的角色
        Role role=new Role();
        role.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Role> allRole = roleMapper.queryAllRole(role);
        //2,查询用户可用的角色
        List<Role> userRole= roleMapper.queryRoleByUid(SysConstant.AVAILABLE_TRUE,userid);

        List<Map<String,Object>> data= new ArrayList<>();
        for (Role r1 : allRole) {
            Boolean LAY_CHECKED=false; //复选框被选中的属性
            for (Role r2 : userRole) {
                if (r2.getRoleid()==r1.getRoleid()){
                    LAY_CHECKED=true;
                }
            }
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    @Override
    public void saveUserRole(UserVo userVo) {
        Integer userid =userVo.getUserid();
        //1.根据userid删除用户的角色
        roleMapper.deleteRoleUserByUid(userid);
        //2.添加用户的角色
        Integer[] roleIds = userVo.getIds();
        if(roleIds!=null&& roleIds.length>0) {
            for (Integer rid : roleIds) {
                userMapper.insertUserRole(userid,rid);
            }
        }

    }

    @Override
    public User queryUserWithOldPwd(UserVo userVo) {
        User user = new User();

        user.setLoginname(userVo.getLoginname());

        String pwd = DigestUtils.md5DigestAsHex(userVo.getOldPwd().getBytes());
        user.setPwd(pwd);

        return userMapper.login(user);
    }
}
