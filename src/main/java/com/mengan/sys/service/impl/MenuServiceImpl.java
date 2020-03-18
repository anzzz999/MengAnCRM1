package com.mengan.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.Menu;
import com.mengan.sys.mapper.MenuMapper;
import com.mengan.sys.service.MenuService;
import com.mengan.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> findAllMenuForList(MenuVo menuVo) {
        List<Menu> menuList = menuMapper.queryAllMenu(menuVo);
        return menuList;
    }

    /**
     *
     * @param menuVo
     * @param userId
     * @return
     */
    @Override
    public List<Menu> findMenuByUserIdForList(MenuVo menuVo, Integer userId) {

        return menuMapper.queryMenuByUid(menuVo.getAvailable(),userId);
    }


    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        //PageHelper.startPage会将下一条语句进行封装，变成分页语句
        Page<Object> page = PageHelper.startPage(menuVo.getPage(), menuVo.getLimit());
        List<Menu> data=menuMapper.queryAllMenu(menuVo);
        DataGridView dataGridView = new DataGridView(page.getTotal(), data);
        return dataGridView;
    }

    @Override
    public void addMenu(MenuVo menuVo) {
        menuMapper.insertSelective(menuVo);
    }

    @Override
    public void updateMenu(MenuVo menuVo) {
        menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    @Override
    public Integer queryMenuByPid(Integer id) {
       return menuMapper.queryMenuByPid(id);
    }

    @Override
    public void deleteMenu(Integer id) {
        //根据菜单id删除sys_role_menu表里的数据
            menuMapper.deleteRoleMenuByMid(id);
            //根据菜单id删除menu表里的数据
            menuMapper.deleteByPrimaryKey(id);
    }
}
