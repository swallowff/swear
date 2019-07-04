package cn.swallowff.modules.core.system.dto;

import cn.swallowff.modules.core.cmomon.entity.TreeNode;

/**
 * @author Administrator
 * @description
 * @create 2019/7/4
 */
public class DeptRoleDto extends TreeNode<DeptRoleDto> {
    private boolean checked;
    private boolean disabled;
    private boolean isLeaf;

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
}
