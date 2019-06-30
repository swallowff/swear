package cn.swallowff.modules.core.system.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;

import java.util.List;

/**
 * 菜单DO
 * @author shenyu
 * @create 2019/5/6
 */
public class Menu extends BaseEntity<Menu> {
    private String code;    //菜单编号
    private String pcode;   //父级菜单编号
    private String pcodes;  //所有父级编号
    private String name;    //菜单名称
    private String icon;    //图标
    private String url;     //url地址
    private Integer sort;    //排序
    private Integer levels;  //级别
    private Boolean isMenu;  //是否是菜单
    private String tips;    //备注
    private Boolean isOpen; //是否打开

    //子菜单列表
    private List<Menu> subMenuList;

    public List<Menu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<Menu> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPcodes() {
        return pcodes;
    }

    public void setPcodes(String pcodes) {
        this.pcodes = pcodes;
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

    public Boolean getMenu() {
        return isMenu;
    }

    public void setMenu(Boolean menu) {
        isMenu = menu;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }
}
