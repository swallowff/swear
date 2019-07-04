package cn.swallowff.modules.core.cmomon.service;

import cn.swallowff.modules.core.cmomon.dao.TreeDao;
import cn.swallowff.modules.core.cmomon.entity.TreeEntity;

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
    public E findTree(E parent){
        List<E> children = crudDao.findChildren(parent);
        if (null == children || children.size() == 0){
            return parent;
        }else {
            updateChildrenNode(children);
        }
        parent.setChildren(children);
        return parent;
    }

    /**
     * TODO 优化查询树,一次查出所有list
     */

    /**
     * 递归查询子节点
     * @param treeNodeList
     */
    private void updateChildrenNode(List<E> treeNodeList){
        for (int i = 0; i < treeNodeList.size() ; i++){
            E node = treeNodeList.get(i);
            List<E> children = crudDao.findChildren(node);
            if (null == children || children.size() == 0){
                break;
            }
            updateChildrenNode(children);
            node.setChildren(children);
        }

    }
}
