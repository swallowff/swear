package cn.swallowff.modules.core.system.dto;

/**
 * @author shenyu
 * @create 2019/7/5
 */
public class DtreeNode {
    private String id;
    private String title;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
