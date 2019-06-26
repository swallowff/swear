package cn.swallowff.modules.core.util;

import cn.swallowff.modules.core.cache.DictCache;
import cn.swallowff.modules.core.system.service.DictService;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author shenyu
 * @create 19-6-26
 */
public class DictUtils {

    private static DictService dictService = SpringContextHolder.getBean(DictService.class);

    /**
     * 自定义字典缓存
     */
    private static final Map<String,List<DictCache>> dictCodeMap = new WeakHashMap<>();

    public static String getLabel(String code,Integer val){
        List<DictCache> labelList = dictCodeMap.get(code);
        if (null == labelList){
            labelList = dictService.getDictCacheList(code);
            dictCodeMap.put(code,labelList);
        }
        for (DictCache dictCache : labelList){
            if (dictCache.getVal() == val){
                return dictCache.getLabel();
            }
        }
        return "未知的字典数据";
    }

    /**
     * 更新字典数据时需要更新缓存
     * @param code
     */
    public static void delKey(String code){
        dictCodeMap.remove(code);
    }



}
