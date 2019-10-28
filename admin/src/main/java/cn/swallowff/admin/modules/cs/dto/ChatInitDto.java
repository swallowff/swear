package cn.swallowff.admin.modules.cs.dto;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/7/20
 */
public class ChatInitDto {
    private ChatUserDto mine;
    private List<ChatFriendGroupDto> friend;
    private List<ChatGroupTeamDto> group;

    public ChatUserDto getMine() {
        return mine;
    }

    public void setMine(ChatUserDto mine) {
        this.mine = mine;
    }

    public List<ChatFriendGroupDto> getFriend() {
        return friend;
    }

    public void setFriend(List<ChatFriendGroupDto> friend) {
        this.friend = friend;
    }

    public List<ChatGroupTeamDto> getGroup() {
        return group;
    }

    public void setGroup(List<ChatGroupTeamDto> group) {
        this.group = group;
    }
}
