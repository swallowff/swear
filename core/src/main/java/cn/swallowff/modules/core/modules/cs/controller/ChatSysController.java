package cn.swallowff.modules.core.modules.cs.controller;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.modules.cs.dto.ChatFriendGroupDto;
import cn.swallowff.modules.core.modules.cs.dto.ChatGroupTeamDto;
import cn.swallowff.modules.core.modules.cs.dto.ChatInitDto;
import cn.swallowff.modules.core.modules.cs.dto.ChatUserDto;
import cn.swallowff.modules.core.modules.cs.entity.CsGroupTeamUser;
import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import cn.swallowff.modules.core.modules.cs.entity.CsUserContactGroup;
import cn.swallowff.modules.core.modules.cs.service.CsGroupTeamUserService;
import cn.swallowff.modules.core.modules.cs.service.CsUserContactGroupService;
import cn.swallowff.modules.core.modules.cs.service.CsUserContactService;
import cn.swallowff.modules.core.modules.cs.service.CsUserService;
import cn.swallowff.modules.core.modules.cs.wrapper.CsUserToChatUserWrapper;
import cn.swallowff.modules.core.modules.cs.wrapper.mappings.CsUserContactGroupMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shenyu
 * @create 2019/7/20
 */
@Controller
@RequestMapping("${swear.path.admin}/sys/chat")
public class ChatSysController {
    @Autowired
    private CsUserService csUserService;
    @Autowired
    private CsGroupTeamUserService groupTeamUserService;
    @Autowired
    private CsUserContactService userContactService;
    @Autowired
    private CsUserContactGroupService userContactGroupService;

    @RequestMapping(value = "init")
    @ResponseBody
    public Object pageInit(String csuid){
        //查询当前用户信息
        CsUser csUser = csUserService.selectById(csuid);
        CsUserToChatUserWrapper wrapper1 = new CsUserToChatUserWrapper(csUser);
        ChatUserDto self = wrapper1.wrap();

        //查询好友列表
        List<CsUserContactGroup> cgList = userContactGroupService.findUserGroups(csuid);
        List<ChatFriendGroupDto> fglist = cgList.stream().map(item ->
                CsUserContactGroupMapping.MAPPING.toChatFriendGroupDto(item)).collect(Collectors.toList());
        for (ChatFriendGroupDto chatFriendGroupDto : fglist){
            chatFriendGroupDto.setList(userContactService.findGroupContacts(chatFriendGroupDto.getId()));
        }
        //查询群组列表
        CsGroupTeamUser q1 = new CsGroupTeamUser();
        q1.setCsUid(csuid);
        List<ChatGroupTeamDto> glist = groupTeamUserService.findUserGroupTeam(q1);

        //组装返回参数
        ChatInitDto initDto = new ChatInitDto();
        initDto.setMine(self);
        initDto.setFriend(fglist);
        initDto.setGroup(glist);

        BaseResp baseResp = BaseResp.newSuccess();
        baseResp.setData(initDto);
        return baseResp;
    }

    @RequestMapping(value = "members")
    @ResponseBody
    public Object members(){

        return BaseResp.newSuccess();
    }

}
