package cn.swallowff.modules.core.modules.system.wrapper;

import cn.swallowff.common.lang.DateUtils;
import cn.swallowff.common.mapper.BeanMapConvert;
import cn.swallowff.modules.core.cmomon.wrapper.BaseWrapper;
import cn.swallowff.modules.core.modules.system.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author shenyu
 * @create 2019/7/2
 */
public class UserDateFormatWrapper extends BaseWrapper<User> {
    public UserDateFormatWrapper(User user) {
        super(user);
    }

    public UserDateFormatWrapper(List<User> entityList) {
        super(entityList);
    }

    @Override
    protected <R> R wrapEntity(User user) {
        Map<String, Object> map = BeanMapConvert.beanToMapObject(user);
        map.put("birthday", DateUtils.formatDate(user.getBirthday(), "yyyy/MM/dd"));
        return (R) map;
    }
}
