package cn.swallowff.admin.modules.system.wrapper;

import cn.swallowff.common.mapper.BeanMapConvert;
import cn.swallowff.admin.cmomon.wrapper.BaseWrapper;
import cn.swallowff.admin.modules.system.entity.User;
import cn.swallowff.admin.util.DictUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description
 * @create 2019/6/28
 */
public class UserAjaxListDictWrapper extends BaseWrapper<User> {

    public UserAjaxListDictWrapper(List<User> entityList) {
        super(entityList);
    }

    @Override
    protected <R> R wrapEntity(User user) {
        Map<String, Object> map = BeanMapConvert.beanToMapObject(user);
        map.put("sex", DictUtils.getLabel("sex", user.getSex()));
        return (R) map;
    }
}
