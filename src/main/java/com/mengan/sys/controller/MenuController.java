package com.mengan.sys.controller;

import com.mengan.sys.constant.SysConstant;
import com.mengan.sys.domain.*;
import com.mengan.sys.service.MenuService;
import com.mengan.sys.utils.ResultObj;
import com.mengan.sys.utils.WebUtils;
import com.mengan.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *  菜单管理控制器
 */

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 删除菜单
     */
    @RequestMapping("deleteMenu")
    public ResultObj  deleteMenu(MenuVo menuVo){
        try {
            this.menuService.deleteMenu(menuVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }

    }


    /**
     * 删除菜单时判断是否还有子节点
     */
@RequestMapping("checkMenuHasChildren")
public ResultObj checkMenuHasChildren(MenuVo menuVo){
    //根据pid查询菜单数量
    Integer count=this.menuService.queryMenuByPid(menuVo.getId());
    if(count>0) {
        return ResultObj.STATUS_TRUE;
    }else {
        return ResultObj.STATUS_FALSE;
    }
}

    /**
 * 修改菜单
 */
@RequestMapping("updateMenu")
    public ResultObj updateMenu(MenuVo menuVo){
    try{
       menuService.updateMenu(menuVo);

    }catch (Exception e){
        e.printStackTrace();
        return ResultObj.UPDATE_ERROR;
    }
    return ResultObj.UPDATE_SUCCESS;
}
    /**
     *添加菜单项
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(MenuVo menuVo){
        try {
            menuService.addMenu(menuVo);
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
        return ResultObj.ADD_SUCCESS;
    }
    /**
     * 添加页面回显数据
     * @param menuVo
     * @return
     */
     @RequestMapping("selectPidForAdd")
    public List<Menu> selectPidForAdd(MenuVo menuVo){
        return   menuService.findAllMenuForList(menuVo);

    }
    /**
     * 加载菜单列表返回DataGridView
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo) {
        return this.menuService.queryAllMenu(menuVo);
    }


    @RequestMapping("loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
        List<Menu> menuList=null;
        User user = (User)WebUtils.getHttpSession().getAttribute("user");
        menuVo.setAvailable(SysConstant.AVAILABLE_TRUE);//只查询可用的
        if (user.getType()==SysConstant.USER_TYPE_SUPER){//超级管理员
            menuList = menuService.findAllMenuForList(menuVo);
        }else{
            menuList = menuService.findMenuByUserIdForList(menuVo,user.getUserid());
        }
        List<TreeNode> nodeList =new ArrayList<>();//简单节点列
        for (Menu menu : menuList) {
            Integer id=menu.getId();
            Integer pid=menu.getPid();
            String title=menu.getTitle();
            String icon=menu.getIcon();
            String href=menu.getHref();
            Boolean spread=menu.getSpread()== SysConstant.SPREAD_TRUE?true:false;
            String target=menu.getTarget();
            TreeNode treeNode = new TreeNode( id, pid,  title,  icon,  href,  spread,  target);
            nodeList.add(treeNode);
        }
        List<TreeNode> treeNodes = TreeNodeBuilder.builder(nodeList, 1);
/*        List<TreeNode> treeNodes=new ArrayList<>();
       for (TreeNode n1 : nodeList) {
            if (n1.getId()==1){
                treeNodes.add(n1);
            }
            for (TreeNode n2 : nodeList) {
                if (n2.getPid().equals(n1.getId())){
                    n1.getChildren().add(n2);
                }
            }
        }*/
        return treeNodes;
    }

}
