package cn.swallowff.modules.core.cmomon.dao;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface CrudDao<E extends BaseEntity<E>> {
    E selectById(String id);

    List<E> findList(E entity);

    int insert(E entity);

    int insertSelective(E entity);

    int update(E entity);

    int updateSelective(E entity);

    int delete(String id);
}
