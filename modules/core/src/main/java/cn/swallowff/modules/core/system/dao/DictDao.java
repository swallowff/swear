package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cache.DictCache;
import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Repository
@Mapper
public interface DictDao extends CrudDao<Dict> {
    List<DictCache> getDictCacheList(String code);

}
