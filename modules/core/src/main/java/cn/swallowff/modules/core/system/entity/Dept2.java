package cn.swallowff.modules.core.system.entity;

import cn.swallowff.modules.core.cmomon.entity.TreeEntity;

import java.io.Serializable;

/**
 * 部门
 */
public class Dept2 extends TreeEntity<Dept2> implements Serializable {
    private Integer sort;   //排序
    private String simpleName;  //简称
    private String fullName;    //全称
    private String tips;        //提示

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
