package cn.swallowff.modules.core.modules.cs.controller;

import cn.swallowff.common.collect.ListUtils;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.modules.cs.dto.ChatGroupTeamDto;
import cn.swallowff.modules.core.modules.cs.dto.ChatInitDto;
import cn.swallowff.modules.core.modules.cs.dto.ChatUserDto;
import cn.swallowff.modules.core.modules.cs.dto.ChatFriendGroupDto;
import cn.swallowff.modules.core.modules.cs.entity.CsFriendGroup;
import cn.swallowff.modules.core.modules.cs.entity.CsGroupTeamUser;
import cn.swallowff.modules.core.modules.cs.entity.CsUser;
import cn.swallowff.modules.core.modules.cs.service.CsFriendGroupService;
import cn.swallowff.modules.core.modules.cs.service.CsFriendGroupUserService;
import cn.swallowff.modules.core.modules.cs.service.CsGroupTeamUserService;
import cn.swallowff.modules.core.modules.cs.service.CsUserService;
import cn.swallowff.modules.core.modules.cs.wrapper.CsUserToChatUserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    private CsFriendGroupService friendGroupService;
    @Autowired
    private CsFriendGroupUserService friendGroupUserService;

//    @RequestMapping(value = "init")
//    @ResponseBody
//    public Object pageInit(){
//        ChatUserDto self = new ChatUserDto();
//        self.setId("1");
//        self.setStatus("online");
//        self.setUsername("swallow");
//        self.setSign("没有话要说");
//        self.setAvatar("http://localhost:8080/swear/upload/img/2019-07-16/393f5b0effb141c6a2ed7c05e99496af.jpeg.png");
//
//        ChatGroupDto group = new ChatGroupDto();
//        group.setId("g1");
//        group.setGroupname("家人群");
//        group.setAvatar("http://localhost:8080/swear/upload/img/2019-07-16/393f5b0effb141c6a2ed7c05e99496af.jpeg.png");
//        List<ChatGroupDto> glist = new ArrayList<>();
//        glist.add(group);
//
//        List<ChatUserDto> list = new ArrayList<>();
//        list.add(self);
//        ChatUserGroupDto userGroup = new ChatUserGroupDto();
//        userGroup.setId("ug1");
//        userGroup.setGroupname("高中同学们");
//        userGroup.setList(list);
//        List<ChatUserGroupDto> flist = new ArrayList<>();
//        flist.add(userGroup);
//
//        ChatInitDto initDto = new ChatInitDto();
//        initDto.setMine(self);
//        initDto.setFriend(flist);
//        initDto.setGroup(glist);
//
//        BaseResp baseResp = BaseResp.newSuccess();
//        baseResp.setData(initDto);
//        return baseResp;
//    }

    @RequestMapping(value = "init")
    @ResponseBody
    public Object pageInit(String csuid){
//        csuid = "1152886134112866304";

        //查询当前用户信息
        CsUser csUser = csUserService.selectById(csuid);
        CsUserToChatUserWrapper wrapper1 = new CsUserToChatUserWrapper(csUser);
        ChatUserDto self = wrapper1.wrap();

        //查询好友列表
        List<ChatFriendGroupDto> fglist = friendGroupService.findFriendGroups(csuid);
        for (ChatFriendGroupDto fgd : fglist){
            List<CsUser> csUsers = friendGroupUserService.findGroupUserJoin(fgd.getId());
            if (ListUtils.isNotEmpty(csUsers)){
                CsUserToChatUserWrapper wrapper2 = new CsUserToChatUserWrapper(csUsers);
                fgd.setList(wrapper2.wrapList());
            }
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
