package cn.swallowff.admin.cmomon.entity;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/4
 */
public class TreeNode<T> {
    private String id;
    private String label;
    private List<T> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
