package com.mengan.sys.mapper;

import com.mengan.sys.domain.Menu;
import com.mengan.sys.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);


    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);


    Integer queryMenuByPid(Integer id);

    void deleteRoleMenuByMid(Integer id);

    /**
     * 根据uid查询可用菜单
     * @param available
     * @param userId
     * @return
     */
    List<Menu> queryMenuByUid(@Param("available") Integer available, @Param("userId") Integer userId);

    /**
     * 根据pid查询数据
     * @param pid
     * @return
     */
    List<Menu> queryMenuByPidForList(Integer pid);

    /**
     * 查询角色对应的菜单
     * @param pid
     * @param available
     * @param roleid
     * @return
     */
    List<Menu> queryMenuByRid(@Param("pid") Integer pid, @Param("available")Integer available, @Param("roleid")Integer roleid);

    /**
     * 根据mid查询菜单及其对应的子菜单
     * @param mid
     * @return
     */
    List<Menu> queryMenuAndChildrenMenu(Integer mid);
}