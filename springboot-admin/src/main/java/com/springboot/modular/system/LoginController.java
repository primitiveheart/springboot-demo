package com.springboot.modular.system;

import com.springboot.common.persistence.dao.UserMapper;
import com.springboot.common.persistence.model.User;
import com.springboot.core.base.controller.BaseController;
import com.springboot.core.node.MenuNode;
import com.springboot.core.shiro.ShiroKit;
import com.springboot.modular.system.dao.MenuDao;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.List;

/**
 * Created by admin on 2018/1/17.
 */
public class LoginController extends BaseController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private UserMapper userMapper;

    /**
     * 跳转到主页
     * @param model
     * @return
     */
    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String index(Model model){
        //获取菜单列表
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        if(roleList == null || roleList.size() == 0){
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "login.html";
        }
        List<MenuNode> menus = menuDao.getMenusByRoleIds(roleList);
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        //需要过滤

        model.addAttribute("titles", titles);
        //获取用户的头像
        Integer id = ShiroKit.getUser().getId();
        User user = userMapper.selectById(id);
        String avatar = user.getAvatar();
        model.addAttribute("avatar", avatar);
        return "index.html";
    }
}
