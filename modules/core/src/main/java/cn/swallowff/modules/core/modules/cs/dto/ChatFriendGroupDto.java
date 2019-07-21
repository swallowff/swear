package cn.swallowff.modules.core.modules.cs.dto;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/20
 */
public class ChatFriendGroupDto {
    private String groupname;
    private String id;
    private List<ChatUserDto> list;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChatUserDto> getList() {
        return list;
    }

    public void setList(List<ChatUserDto> list) {
        this.list = list;
    }
}
