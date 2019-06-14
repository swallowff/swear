package cn.swallowff.modules.core.cmomon.wrapper;

import cn.swallowff.common.json.JacksonUtil;
import cn.swallowff.modules.core.system.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenyu
 * @create 19-6-14
 */
public class TestUserWrapper extends BaseDataWrapper<User> {

    public TestUserWrapper(List<User> sourceList) {
        super(sourceList);
    }

    public TestUserWrapper(User user) {
        super(user);
    }

    @Override
    protected <R> R wrapToEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return (R) userDto;
    }

    @Override
    protected Map<String, Object> wrapToMap(User user) {
        Map<String,Object> rmap = new HashMap<>();
        rmap.put("id2",user.getId());
        rmap.put("name2",user.getName());
        rmap.put("sex2",user.getSex());
        return rmap;
    }

    public static void main(String[] args){
        User user = new User();
        user.setId("1");
        user.setName("user");
        TestUserWrapper userWrapper = new TestUserWrapper(user);
        UserDto userDto = userWrapper.wrapEntity();
        Map<String,Object> umap = userWrapper.wrapMap();
        System.out.println(JacksonUtil.toJson(user));
        System.out.println(JacksonUtil.toJson(userDto));
        System.out.println(JacksonUtil.toJson(umap));

    }
}
