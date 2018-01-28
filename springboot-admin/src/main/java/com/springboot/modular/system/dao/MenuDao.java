package com.springboot.modular.system.dao;

import com.springboot.core.node.MenuNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/1/17.
 */
public interface MenuDao {

    /**
     * 根据条件查询菜单
     * @param condition
     * @param level
     * @return
     */
    List<Map<String, Object>> selectMenus(@Param("condition") String condition, @Param("level") String level);

    /**
     * 根据角色id，得到所有的菜单id
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 通过角色获取菜单
     * @param roleIds
     * @return
     */
    List<MenuNode> getMenusByRoleIds(List<Integer> roleIds);
}
