package cn.swallowff.modules.core.cmomon.service;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class CrudService<M extends CrudDao<E>,E extends BaseEntity<E>> {
    @Autowired
    protected M crudDao;

    public E selectById(String id){
        return crudDao.selectById(id);
    }

    public List<E> findList(E entity){
        return crudDao.findList(entity);
    }

    public void save(E entity){
        if (entity.getIsNewRecord()){
            entity.preInsert();
            crudDao.insert(entity);
        }else {
            entity.preUpdate();
            crudDao.update(entity);
        }
    }

    public int insert(E entity){
        entity.preInsert();
        return crudDao.insert(entity);
    }

    public int insertSelective(E entity){
        entity.preInsert();
        return crudDao.insertSelective(entity);
    }

    public int update(E entity){
        entity.preUpdate();
        return crudDao.update(entity);
    }

    public int updateSelective(E entity){
        entity.preUpdate();
        return crudDao.updateSelective(entity);
    }

    public int delete(String id){
        return crudDao.delete(id);
    }

    protected <T> PageResp<T> pageResp(PageInfo<T> pageInfo){
        PageResp<T> pageResp = new PageResp<>();
        pageResp.setDataList(pageInfo.getList());
        pageResp.setPageNum(pageInfo.getPageNum());
        pageResp.setPageSize(pageInfo.getPageSize());
        pageResp.setTotalPage(pageInfo.getPages());
        pageResp.setTotalRows(pageInfo.getTotal());
        return pageResp;
    }


}
