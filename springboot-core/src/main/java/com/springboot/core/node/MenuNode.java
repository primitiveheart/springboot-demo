package com.springboot.core.node;

import com.springboot.core.constant.IsMenu;

import java.util.*;

/**
 * Created by admin on 2018/1/17.
 */
public class MenuNode implements Comparable {
    //节点id
    private Long id;
    //父节点的id
    private Long parentId;
    //节点的名称
    private String name;
    //按钮的级别
    private Integer level;
    //是否有子菜单
    private Integer isMenu;
    //按钮的排序
    private Integer num;
    //节点的url
    private String url;
    //节点的图标
    private String icon;
    //节点的集合
    private List<MenuNode> children;
    //查询子节点的临时节点
    private List<MenuNode> linkedList = new ArrayList<MenuNode>();

    public MenuNode(){super();}

    public MenuNode(Long id, Long parentId){
        super();
        this.id = id;
        this.parentId = parentId;
    }


    @Override
    public int compareTo(Object o) {
        MenuNode menuNode = (MenuNode) o;
        Integer num = menuNode.getNum();
        if(num == null){
            num = 0;
        }
        return this.num.compareTo(num);
    }

    /**
     * 构建菜单树
     * @param nodeList
     */
    public void buildNodeTree(List<MenuNode> nodeList){
        for(MenuNode treeNode : nodeList){
            List<MenuNode> childerns = treeNode.findChildNodes(nodeList, treeNode.getId());
            if(childerns.size() > 0){
                treeNode.setChildren(childerns);
            }
        }
    }

    /**
     * 查询子节点的集合
     * @param nodeList
     * @param parentId
     * @return
     */
    public List<MenuNode> findChildNodes(List<MenuNode> nodeList, Long parentId){
        if(nodeList == null || parentId == null){
            return null;
        }
        for(Iterator<MenuNode> iterator = nodeList.iterator(); iterator.hasNext();){
            MenuNode node = iterator.next();
            //得到某个父节点下的所有子节点
            if(node.getParentId() != 0 && parentId.equals(node.getParentId())){
                recursionTraverse(nodeList, node, parentId);
            }
        }

        return linkedList;
    }

    /**
     * 遍历每一个节点的子节点
     * @param nodeList
     * @param node
     * @param pId
     */
    public void recursionTraverse(List<MenuNode> nodeList, MenuNode node, Long pId){
        List<MenuNode> childList = getChildList(nodeList, node);
        if(childList.size() > 0){
            if(node.getParentId().equals(pId)){
                linkedList.add(node);
            }
            //TODO我觉得这里有问题
            Iterator<MenuNode> it = childList.iterator();
            while (it.hasNext()) {
                MenuNode n = (MenuNode) it.next();
                recursionTraverse(nodeList, n, pId);
            }
        }else{
            if(node.getParentId().equals(pId)){
                linkedList.add(node);
            }
        }
    }

    /**
     * 得到某个节点的所有子节点列表
     * @param nodeList
     * @param node
     * @return
     */
    public List<MenuNode> getChildList(List<MenuNode> nodeList, MenuNode node){
        List<MenuNode> list = new ArrayList<MenuNode>();
        Iterator<MenuNode> iterator = nodeList.iterator();
        while(iterator.hasNext()){
            MenuNode menuNode = iterator.next();
            if(menuNode.getParentId().equals(node.getId())){
                list.add(menuNode);
            }
        }
        return list;
    }

    /**
     * 清除按钮级别的资源
     * @param list
     * @return
     */
    public static List<MenuNode> clearBtn(List<MenuNode> list){
        List<MenuNode> noBtns = new ArrayList<MenuNode>();
        for(MenuNode menuNode : list){
            if(menuNode.getIsMenu() == IsMenu.YES.getCode()){
                noBtns.add(menuNode);
            }
        }
        return noBtns;
    }

    /**
     * 清除所有的二级菜单
     * @param nodeList
     * @return
     */
    public static List<MenuNode> clearLevelTwo(List<MenuNode> nodeList){
        List<MenuNode> results = new ArrayList<MenuNode>();
        for(MenuNode node : nodeList){
            Integer level = node.getLevel();
            if(level.equals(1)){
                results.add(node);
            }
        }
        return results;
    }

    /**
     * 构建菜单列表
     * @param nodeList
     * @return
     */
    public static List<MenuNode> buildTitle(List<MenuNode> nodeList){
        List<MenuNode> clearBtn = clearBtn(nodeList);
        new MenuNode().buildNodeTree(clearBtn);
        List<MenuNode> menuNodes = clearLevelTwo(clearBtn);

        //对菜单进行排序
        Collections.sort(menuNodes);

        //对菜单的子菜单排序
        for(MenuNode menuNode : menuNodes){
            if(menuNode.getChildren() != null && menuNode.getChildren().size() > 0){
                Collections.sort(menuNode.getChildren());
            }
        }

        return menuNodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }

    public List<MenuNode> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(List<MenuNode> linkedList) {
        this.linkedList = linkedList;
    }

    @Override
    public String toString() {
        return "MenuNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", isMenu=" + isMenu +
                ", num=" + num +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                ", linkedList=" + linkedList +
                '}';
    }
}
