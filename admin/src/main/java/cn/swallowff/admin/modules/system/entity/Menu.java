package cn.swallowff.admin.modules.system.entity;

import cn.swallowff.admin.cmomon.entity.TreeEntity;

/**
 * 菜单DO
 *
 * @author shenyu
 * @create 2019/5/6
 */
public class Menu extends TreeEntity<Menu> {
    private String code;    //菜单编号
    private String name;    //菜单名称
    private String icon;    //图标
    private String url;     //url地址
    private Integer sort;    //排序
    private Integer levels;  //级别
    private Boolean isMenu;  //是否是菜单
    private Boolean isExpandable; //是否可展开
    private String tips;    //备注
    private Boolean isOpen; //是否打开

    //用户菜单权限查询字段
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public Boolean getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Boolean menu) {
        isMenu = menu;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean open) {
        isOpen = open;
    }

    public Boolean getIsExpandable() {
        return isExpandable;
    }

    public void setIsExpandable(Boolean expandable) {
        isExpandable = expandable;
    }
}
