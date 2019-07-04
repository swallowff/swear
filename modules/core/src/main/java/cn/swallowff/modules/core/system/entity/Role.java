package cn.swallowff.modules.core.system.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;

public class Role extends BaseEntity {
    private Integer sort;    //排序
    private String name;    //角色名称
    private Integer pid;    //角色父级id
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
}
