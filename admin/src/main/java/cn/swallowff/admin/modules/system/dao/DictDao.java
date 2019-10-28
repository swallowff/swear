package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.entity.Dict;
import cn.swallowff.admin.modules.system.dto.DictCache;
import cn.swallowff.admin.cmomon.dao.CrudDao;
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
