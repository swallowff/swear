package cn.swallowff.modules.core.cmomon.entity;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/4
 */
public class TreeEntity<E extends TreeEntity> extends BaseEntity{
    private E parent;
    private String pids;
    private List<E> children;

    public E getParent() {
        return parent;
    }

    public void setParent(E parent) {
        this.parent = parent;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public List<E> getChildren() {
        return children;
    }

    public void setChildren(List<E> children) {
        this.children = children;
    }
}
