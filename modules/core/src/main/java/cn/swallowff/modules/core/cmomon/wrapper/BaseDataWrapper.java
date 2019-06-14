package cn.swallowff.modules.core.cmomon.wrapper;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author shenyu
 * @create 19-6-14
 */
public abstract class BaseDataWrapper<T> {
    private T t;

    private List<T> sourceList;

    public BaseDataWrapper(List<T> sourceList){
        this.sourceList = sourceList;
    }

    public BaseDataWrapper(T t){
        this.t = t;
    }

    public <R> R wrapEntity(){
        if (null != t){
            return this.wrapToEntity(t);
        }else return null;
    }

    public <R> List<R> wrapEntityList() {
        if (CollectionUtils.isNotEmpty(sourceList)) {
            Iterator<T> iterator = sourceList.iterator();
            List<R> resList = new LinkedList<>();
            while (iterator.hasNext()) {
                resList.add(wrapToEntity(t));
            }
            return resList;
        }else return Collections.EMPTY_LIST;
    }

    public Map<String,Object> wrapMap(){
        if (null != t){
            return wrapToMap(t);
        }else return null;
    }

    public List<Map<String,Object>> wrapMapList(){
        if (CollectionUtils.isNotEmpty(sourceList)){
            Iterator<T> iterator = sourceList.iterator();
            List<Map<String,Object>> resList = new LinkedList<>();
            while (iterator.hasNext()) {
                resList.add(wrapToMap(t));
            }
            return resList;
        }else return Collections.EMPTY_LIST;
    }

    protected abstract <R> R wrapToEntity(T t);

    protected abstract Map<String,Object> wrapToMap(T t);
}
