package cn.swallowff.admin.modules.system.entity;

import cn.swallowff.admin.cmomon.entity.BaseEntity;

public class Dict extends BaseEntity {
    private String name;    //类型名称
    private String code;    //字典code
    private String label;    //标签名
    private Integer val;    //标签值
    private Integer sort;   //排序
    private String tips;    //提示

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
