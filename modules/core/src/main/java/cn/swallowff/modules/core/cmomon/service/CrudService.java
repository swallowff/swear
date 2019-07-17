package cn.swallowff.modules.core.cmomon.service;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public abstract class CrudService<M extends CrudDao<E>, E extends BaseEntity> {
    @Autowired
    protected M crudDao;

    public E selectById(String id) {
        return crudDao.selectById(id);
    }

    public PageResp<E> findPage(E entity) {
        PageHelper.startPage(entity.getPage(), entity.getLimit());
        List<E> list = findList(entity);
        PageInfo<E> pageInfo = new PageInfo<>(list);
        return pageResp(pageInfo);
    }

    public E findEntity(E entity) {
        return crudDao.findEntity(entity);
    }

    public List<E> findList(E entity) {
        return crudDao.findList(entity);
    }

    public int save(E entity) {
        if (entity.getIsNewRecord()) {
            entity.preInsert();
            return crudDao.insert(entity);
        } else {
            entity.preUpdate();
            return crudDao.update(entity);
        }
    }

    public int insert(E entity) {
        entity.preInsert();
        return crudDao.insert(entity);
    }

    public int insertSelective(E entity) {
        entity.preInsert();
        return crudDao.insertSelective(entity);
    }

    public int update(E entity) {
        entity.preUpdate();
        return crudDao.update(entity);
    }

    public int updateSelective(E entity) {
        entity.preUpdate();
        return crudDao.updateSelective(entity);
    }

    public int delete(String id) {
        return crudDao.delete(id);
    }

    protected <T> PageResp<T> pageResp(PageInfo<T> pageInfo) {
        PageResp<T> pageResp = new PageResp<>();
        pageResp.setDataList(pageInfo.getList());
        pageResp.setPageNum(pageInfo.getPageNum());
        pageResp.setPageSize(pageInfo.getPageSize());
        pageResp.setTotalPage(pageInfo.getPages());
        pageResp.setTotalRows(pageInfo.getTotal());
        return pageResp;
    }


}
