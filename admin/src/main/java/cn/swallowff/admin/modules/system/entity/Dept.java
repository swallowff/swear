package cn.swallowff.admin.modules.system.entity;

import cn.swallowff.admin.cmomon.entity.TreeEntity;

import java.io.Serializable;

/**
 * 部门
 */
public class Dept extends TreeEntity<Dept> implements Serializable {
    //    private String pid;
    private Integer sort;   //排序
    private String simpleName;  //简称
    private String fullName;    //全称
    private String tips;        //提示

    public Dept() {
    }

    public Dept(String id) {
        super(id);
    }

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

//    public String getPid() {
//        if (null == getParent()){
//            return null;
//        }else {
//            return getParent().getId();
//        }
//    }
//
//    public void setPid(String pid) {
//        if (null == getParent()){
//            setParent(new Dept(pid));
//        }
//    }
}
