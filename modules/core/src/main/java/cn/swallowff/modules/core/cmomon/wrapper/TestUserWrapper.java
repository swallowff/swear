package cn.swallowff.modules.core.cmomon.wrapper;

import cn.swallowff.common.mapper.BeanMapConvert;
import cn.swallowff.modules.core.system.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shenyu
 * @create 19-6-14
 */
public class TestUserWrapper extends BaseWrapper<User> {

    public TestUserWrapper(User user) {
        super(user);
    }

    public TestUserWrapper(List<User> entityList) {
        super(entityList);
    }

    @Override
    protected <R> R wrapEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName("userDto");
        userDto.setPhone(user.getPhone());
        return (R) userDto;
    }

    public static void main(String[] args){
        User user = new User();
        user.setId("1");
        user.setName("user");
        user.setPhone("13647617481");
        List<User> users = new ArrayList<>();
        users.add(user);

        Map<String,Object> sourceMap = BeanMapConvert.beanToMapObject(user);
        List<Map<String,Object>> sourceMapList = new ArrayList<>();
        sourceMapList.add(sourceMap);

//        TestUserWrapper userWrapper = new TestUserWrapper(user);
//        UserDto userDto = userWrapper.entityToEntity();
//        Map<String,Object> umap = userWrapper.entityToMap();
////        System.out.println(JacksonUtil.toJson(user));
//        System.out.println(JacksonUtil.toJson(userDto));
//        System.out.println(JacksonUtil.toJson(umap));

//        TestUserWrapper userListWrapper = new TestUserWrapper(users);
//        List<UserDto> userDtoList = userListWrapper.doWrapList();
//        System.out.println(JacksonUtil.toJson(userDtoList));

//        TestUserWrapper userListWrapper = new TestUserWrapper(sourceMapList);

    }


}
