package cn.swallowff.modules.core.system.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;

public class Dict extends BaseEntity<Dict> {
//    private String pid;    //父级id
    private String code;    //字典code
    private String name;    //字典名
    private Integer val;    //字典值
    private Integer sort;   //排序
    private String tips;    //提示

//    public String getPid() {
//        return pid;
//    }
//
//    public void setPid(String pid) {
//        this.pid = pid;
//    }


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

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
