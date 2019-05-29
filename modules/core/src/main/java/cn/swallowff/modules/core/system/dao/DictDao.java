package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Mapper
public interface DictDao extends CrudDao<Dict> {
    List<Dict> selectByParentCode(String parentCode);
}
