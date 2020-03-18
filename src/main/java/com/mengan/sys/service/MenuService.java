package com.mengan.sys.service;


import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.domain.Menu;
import com.mengan.sys.vo.MenuVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单接口
 */
public interface MenuService {

    /**
     * 查询所有菜单返回
     * @param menuVo
     * @return
     */
     List<Menu> findAllMenuForList(MenuVo menuVo);

    /**
     * 根据userid查询用户的可用菜单
     * @param menuVo
     * @param userId
     * @return
     */
    List<Menu> findMenuByUserIdForList(MenuVo menuVo,Integer userId);

    /**
     * 查询所有的菜单
     * @param menuVo
     * @return
     */
    DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 添加菜单
     * @param menuVo
     */
    void addMenu(MenuVo menuVo);

    /**
     * 更新菜单
     * @param menuVo
     */
    void updateMenu(MenuVo menuVo);

    /**
     * 删除菜单时判断是否还有子节点
     * @param id
     * @return
     */
    Integer queryMenuByPid(Integer id);
    /**
     * 删除菜单
     */
    void deleteMenu(Integer id);
}
