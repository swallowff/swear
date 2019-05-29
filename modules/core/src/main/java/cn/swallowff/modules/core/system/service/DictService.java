package cn.swallowff.modules.core.system.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.modules.core.system.dao.DictDao;
import cn.swallowff.modules.core.system.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Service
public class DictService extends CrudService<DictDao, Dict> {
    @Autowired
    private DictDao dictDao;

    public List<Dict> selectByParentCode(String parentCode){
        return dictDao.selectByParentCode(parentCode);
    }
}
