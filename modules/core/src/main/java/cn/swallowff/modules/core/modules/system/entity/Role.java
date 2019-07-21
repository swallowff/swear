package cn.swallowff.modules.core.modules.system.entity;

import cn.swallowff.modules.core.cmomon.entity.TreeEntity;

import java.io.Serializable;

public class Role extends TreeEntity<Role> implements Serializable {
    private Integer sort;    //排序
    private String name;    //角色名称
    private String code;     //角色编码
    private String deptId;  //部门id
    private String tips;    //提示

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
