package cn.swallowff.admin.util;

import cn.swallowff.admin.modules.system.dto.DictCache;
import cn.swallowff.admin.modules.system.service.DictService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author shenyu
 * @description 字典缓存工具
 * @create 19-6-26
 */
public class DictUtils {

    private static DictService dictService = SpringContextHolder.getBean(DictService.class);

    private static final String TIPS = "未知";

    private static final Map<String, List<DictCache>> dictCacheMap = new WeakHashMap<>();

    public static String getLabel(String code, Integer val) {
        List<DictCache> labelList = dictCacheMap.get(code);
        if (CollectionUtils.isEmpty(labelList)) {
            Assert.notNull(dictService, "dictService must not be null");
            labelList = dictService.getDictCacheList(code);
            if (CollectionUtils.isNotEmpty(labelList)) {
                dictCacheMap.put(code, labelList);
            }
        }
        for (DictCache dictCache : labelList) {
            if (dictCache.getVal() == val) {
                return dictCache.getLabel();
            }
        }
        return TIPS;
    }

    public static List<DictCache> getDictByCode(String code) {
        List<DictCache> cacheList = dictCacheMap.get(code);
        if (CollectionUtils.isEmpty(cacheList)) {
            Assert.notNull(dictService, "dictService must not be null ");
            cacheList = dictService.getDictCacheList(code);
            if (CollectionUtils.isNotEmpty(cacheList)) {
                dictCacheMap.put(code, cacheList);
            }
        }
        return cacheList;
    }

    /**
     * 更新字典数据时需要更新缓存 可使用aop处理
     *
     * @param code
     */
    public static void delKey(String code) {
        dictCacheMap.remove(code);
    }


}
