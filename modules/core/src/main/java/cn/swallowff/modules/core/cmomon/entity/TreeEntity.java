package cn.swallowff.modules.core.cmomon.entity;

import cn.swallowff.common.reflect.ReflectUtils;
import cn.swallowff.modules.core.modules.system.entity.Dept;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/4
 */
public class TreeEntity<E extends TreeEntity> extends BaseEntity {
    public static final String ROOT_ID = "0";
    public static final String ROOT_NAME = "顶级";

    private E parent;
    private String pids;
    private List<E> children;

    public TreeEntity() {
    }

    public TreeEntity(String id) {
        this.setId(id);
    }

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

    public String getPid() {
        if (null == getParent()) {
            return null;
        } else {
            return getParent().getId();
        }
    }

    public void setPid(String pid) {
        if (null == getParent()) {
            Class<E> entityClass = ReflectUtils.getClassGenricType(getClass(), 0);
            try {
                E parent = entityClass.getConstructor().newInstance();
                parent.setId(pid);
                setParent(parent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            parent.setId(pid);
        }
    }

}
