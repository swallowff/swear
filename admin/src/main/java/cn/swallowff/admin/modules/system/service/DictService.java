package cn.swallowff.admin.modules.system.service;

import cn.swallowff.admin.cmomon.service.CrudService;
import cn.swallowff.admin.modules.system.dto.DictCache;
import cn.swallowff.admin.modules.system.dao.DictDao;
import cn.swallowff.admin.modules.system.entity.Dict;
import cn.swallowff.admin.util.DictUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/3/15
 */
@Service
public class DictService extends CrudService<DictDao, Dict> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DictDao dictDao;

    public List<Dict> selectByCode(String code) {
        Dict dict = new Dict();
        dict.setCode(code);
        return super.findList(dict);
    }

    public Dict selectByCodeAndVal(String code, Integer val) {
        Dict dict = new Dict();
        dict.setCode(code);
        dict.setVal(val);
        return super.findEntity(dict);
    }

    public String getLabelName(String code, Integer val) {
        Dict dict = new Dict();
        dict.setCode(code);
        dict.setVal(val);
        dict = super.findEntity(dict);
        return dict == null ? "未知" : dict.getLabel();
    }

    public List<DictCache> getDictCacheList(String code) {
        logger.info("初始化字典数据：code[{}]", code);
        return dictDao.getDictCacheList(code);
    }

    @Override
    public int save(Dict entity) {
        int r = super.save(entity);
        if (r == 1) {
            DictUtils.delKey(entity.getCode());
        }
        return r;
    }

    @Override
    public int update(Dict entity) {
        int r = super.update(entity);
        if (r == 1) {
            DictUtils.delKey(entity.getCode());
        }
        return r;
    }

    @Override
    public int updateSelective(Dict entity) {
        int r = super.updateSelective(entity);
        if (r == 1) {
            DictUtils.delKey(entity.getCode());
        }
        return r;
    }
}
