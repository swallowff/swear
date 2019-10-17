package cn.swallowff.common.framework.base;

import java.util.List;

public interface CrudDao<E extends BaseEntity> {
    E selectById(String id);

    List<E> findList(E entity);

    E findEntity(E entity);

    int insert(E entity);

    int insertSelective(E entity);

    int update(E entity);

    int updateSelective(E entity);

    int delete(String id);
}
