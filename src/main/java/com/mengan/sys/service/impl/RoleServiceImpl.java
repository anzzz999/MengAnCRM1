package com.mengan.sys.service.impl;

import com.github.pagehelper.Constant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.sys.constant.SysConstant;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.Menu;
import com.mengan.sys.domain.Role;
import com.mengan.sys.mapper.MenuMapper;
import com.mengan.sys.mapper.RoleMapper;
import com.mengan.sys.service.RoleService;
import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.vo.MenuVo;
import com.mengan.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        return roleMapper.queryAllRole(roleVo);
    }

    /**
     * 后期权限管理完成之后再来改
     */
    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return roleMapper.queryAllRole(roleVo);
    }

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<Role> data = this.roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addRole(RoleVo roleVo) {
        this.roleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        this.roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public void deleteRole(Integer roleid) {
        // 删除角色表的数据
        this.roleMapper.deleteByPrimaryKey(roleid);
        // 根据角色id删除sys_role_meun里面的数据
        this.roleMapper.deleteRolMenuByRid(roleid);
        // 根据角色id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByRid(roleid);

    }

    @Override
    public DataGridView queryRoleMenu(Integer roleid) {
        //1,查询所有可用的菜单
        Menu menu = new Menu();
        menu.setPid(SysConstant.PID_IS_ONE);
        menu.setAvailable(SysConstant.AVAILABLE_TRUE);
       List<Menu> allMenu =  menuMapper.queryMenuByPidForList(menu.getPid());
        //2,查询角色可用的菜单
       List<Menu> roleMenu= menuMapper.queryMenuByRid(menu.getPid(),menu.getAvailable(),roleid);


        List<Map<String,Object>> data= new ArrayList<>();
        for (Menu m1 : allMenu) {
            Boolean LAY_CHECKED = false; //复选框被选中的属性
            for (Menu m2 : roleMenu) {
                if (m2.getId() == m1.getId()) {
                    LAY_CHECKED = true;
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", m1.getId());
            map.put("title", m1.getTitle());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid=roleVo.getRoleid();
        Integer [] mids=roleVo.getIds();
        //根据rid删除sys_role_menu里面所有数据
        this.roleMapper.deleteRolMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : mids) {
            List<Menu> menuList=menuMapper.queryMenuAndChildrenMenu(mid);
            for (Menu menu : menuList) {
            this.roleMapper.insertRoleMenu(rid,menu.getId());

            }
        }
    }



   /* @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        // 查询所有的可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        // 根据角色ID查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE, roleid);

        List<TreeNode> data = new ArrayList<>();
        for (Menu m1 : allMenu) {
            String checkArr = SysConstast.CODE_ZERO+"";
            for (Menu m2 : roleMenu) {
                if (m1.getId() == m2.getId()) {
                    checkArr = SysConstast.CODE_ONE+"";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(id, pid, title, spread, checkArr));
        }
        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid=roleVo.getRoleid();
        Integer [] mids=roleVo.getIds();
        //根据rid删除sys_role_menu里面所有数据
        this.roleMapper.deleteRolMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : mids) {
            this.roleMapper.insertRoleMenu(rid,mid);
        }
    }*/
}
