package cn.swallowff.modules.core.system.dto;

import java.util.List;

/**
 * @author Administrator
 * @description
 * @create 2019/7/4
 */
public class DeptRoleDto {
    private String id;
    private String label;
    private boolean checked;
    private boolean disabled;
    private boolean isLeaf;
    private List<DeptRoleDto> children;

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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public List<DeptRoleDto> getChildren() {
        return children;
    }

    public void setChildren(List<DeptRoleDto> children) {
        this.children = children;
    }
}
