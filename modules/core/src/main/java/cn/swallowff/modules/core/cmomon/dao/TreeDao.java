package cn.swallowff.modules.core.cmomon.dao;

import cn.swallowff.modules.core.cmomon.entity.TreeEntity;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/4
 */
public interface TreeDao<E extends TreeEntity> extends CrudDao<E> {
    List<E> findChildren(E parent);

    int deleteTree(String id);
}
