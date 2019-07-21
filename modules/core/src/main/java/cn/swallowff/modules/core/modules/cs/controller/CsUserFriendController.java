package cn.swallowff.modules.core.modules.cs.controller;

import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.modules.cs.entity.CsUserFriend;
import cn.swallowff.modules.core.modules.cs.service.CsUserFriendService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author swallowff
 * @create 2019/07/21
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/cs/csUserFriend")
public class CsUserFriendController extends BaseController {

    @Autowired
    private CsUserFriendService csUserFriendService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cs-csUserFriend-list")
    public String listHtml(){
        return "/pages/admin/cs/csUserFriend/csUserFriend-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/cs/csUserFriend/csUserFriend-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        CsUserFriend csUserFriend = csUserFriendService.selectById(id);
        model.addAttribute("csUserFriend",csUserFriend);
        return "/pages/admin/cs/csUserFriend/csUserFriend-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(CsUserFriend csUserFriend){
        PageResp<CsUserFriend> pageResp = csUserFriendService.findPage(csUserFriend);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(CsUserFriend csUserFriend){
        BaseResp baseResp = BaseResp.newSuccess();
        csUserFriendService.save(csUserFriend);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(CsUserFriend csUserFriend){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == csUserFriend || StringUtils.isBlank(csUserFriend.getId())){
            return baseResp.paramsError();
        }
        int r = csUserFriendService.updateSelective(csUserFriend);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = csUserFriendService.delete(id);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "batchDel")
    @ResponseBody
    public BaseResp batchDel(String[] ids){
        BaseResp baseResp = BaseResp.newSuccess();
        int count = 0;
        for (String id : ids){
            int r = csUserFriendService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
