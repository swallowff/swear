package cn.swallowff.modules.core.cmomon.service;

import cn.swallowff.modules.core.cmomon.dao.TreeDao;
import cn.swallowff.modules.core.cmomon.entity.TreeEntity;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/4
 */
public abstract class TreeService<M extends TreeDao<E>,E extends TreeEntity> extends CrudService<M,E>{

    public int save(E entity){
        E parent = (E) entity.getParent();
        if (null != parent){
            String pids = parent.getId();
            if (parent.getPids() != null && !"".equals(parent.getPids())){
                pids = parent.getPids().concat("," + pids);
            }
            entity.setPids(pids);
        }
        return super.save(entity);
    }

    /**
     * 通过递归循环查询查出所有子节点
     * @param parent
     * @return
     */
    public E findChildren(E parent){
        List<E> children = crudDao.findChildren(parent);
        if (CollectionUtils.isEmpty(children)){
            return parent;
        }else {
            updateChildrenNode(children);
        }
        parent.setChildren(children);
        return parent;
    }

    /**
     * TODO 优化查询树结构,一次查出所有list
     */

    /**
     * 递归查询子节点
     * @param treeNodeList
     */
    private void updateChildrenNode(List<E> treeNodeList){
        for (E node : treeNodeList){
            List<E> children = crudDao.findChildren(node);
            if (CollectionUtils.isEmpty(children)){
                break;
            }
            updateChildrenNode(children);
            node.setChildren(children);
        }

    }
}
