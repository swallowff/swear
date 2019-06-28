package cn.swallowff.modules.core.system.wrapper;

import cn.swallowff.common.mapper.BeanMapConvert;
import cn.swallowff.modules.core.cmomon.wrapper.BaseWrapper;
import cn.swallowff.modules.core.system.entity.User;
import cn.swallowff.modules.core.util.DictUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description
 * @create 2019/6/28
 */
public class UserAjaxListDictWrapper extends BaseWrapper<User> {

    public UserAjaxListDictWrapper(User user) {
        super(user);
    }

    public UserAjaxListDictWrapper(List<User> entityList) {
        super(entityList);
    }

    @Override
    protected <R> R wrapEntity(User user) {
        Map<String,Object> map = BeanMapConvert.beanToMapObject(user);
        map.put("sex",DictUtils.getLabel("sex",user.getSex()));
        return (R) map;
    }
}
