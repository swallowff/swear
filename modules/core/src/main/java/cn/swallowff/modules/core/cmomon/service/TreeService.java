package cn.swallowff.modules.core.cmomon.service;

import cn.swallowff.common.reflect.ReflectUtils;
import cn.swallowff.modules.core.cmomon.dao.TreeDao;
import cn.swallowff.modules.core.cmomon.entity.TreeEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/4
 */
public abstract class TreeService<M extends TreeDao<E>,E extends TreeEntity> extends CrudService<M,E>{

    @Override
    public int save(E entity){
        pre(entity);
        return super.save(entity);
    }

    @Override
    public int insert(E entity){
        pre(entity);
        return super.insert(entity);
    }

    @Override
    public int insertSelective(E entity){
        pre(entity);
        return super.insertSelective(entity);
    }

    @Override
    public int update(E entity){
        pre(entity);
        return super.update(entity);
    }

    @Override
    public int updateSelective(E entity){
        pre(entity);
        return super.updateSelective(entity);
    }

    private void pre(E entity){
        E parent = (E) entity.getParent();
        if (null != parent && StringUtils.isNotBlank(parent.getId())){
            parent = crudDao.selectById(parent.getId());
            String pids = null;
            if (null != parent){
                pids = parent.getId();
                if (parent.getPids() != null && !"".equals(parent.getPids())){
                    pids = parent.getPids().concat("," + pids);
                }
            }
            entity.setPids(pids);
        }else {
            entity.setPid(TreeEntity.ROOT_ID);
            entity.setPids(TreeEntity.ROOT_ID);
        }
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
            updateChildrenNode(children,parent);
        }
        parent.setChildren(children);
        return parent;
    }

    public E findAllTree(){
        Class<E> entityClass = ReflectUtils.getClassGenricType(getClass(), 1);
        try {
            E parent = entityClass.getConstructor().newInstance();
            parent.setId(TreeEntity.ROOT_ID);
            return findTree(parent);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int deleteTree(String id){
        return crudDao.deleteTree(id);
    }

    /**
     * 递归查询子节点
     * @param treeNodeList
     */
    private void updateChildrenNode(List<E> treeNodeList,E parent){
        for (int i = 0; i < treeNodeList.size() ; i++){
            E node = treeNodeList.get(i);
            node.setOrderBy(parent.getOrderBy());
            List<E> children = crudDao.findChildren(node);
            if (null != children && children.size() != 0){
                updateChildrenNode(children,parent);
                node.setChildren(children);
            }
        }
    }





}
