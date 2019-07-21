package cn.swallowff.modules.core.modules.cs.controller;

import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.modules.cs.entity.CsFriendGroupUser;
import cn.swallowff.modules.core.modules.cs.service.CsFriendGroupUserService;
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
@RequestMapping(value = "${swear.path.admin}/cs/csFriendGroupUser")
public class CsFriendGroupUserController extends BaseController {

    @Autowired
    private CsFriendGroupUserService csFriendGroupUserService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cs-csFriendGroupUser-list")
    public String listHtml(){
        return "/pages/admin/cs/csFriendGroupUser/csFriendGroupUser-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/cs/csFriendGroupUser/csFriendGroupUser-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        CsFriendGroupUser csFriendGroupUser = csFriendGroupUserService.selectById(id);
        model.addAttribute("csFriendGroupUser",csFriendGroupUser);
        return "/pages/admin/cs/csFriendGroupUser/csFriendGroupUser-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(CsFriendGroupUser csFriendGroupUser){
        PageResp<CsFriendGroupUser> pageResp = csFriendGroupUserService.findPage(csFriendGroupUser);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(CsFriendGroupUser csFriendGroupUser){
        BaseResp baseResp = BaseResp.newSuccess();
        csFriendGroupUserService.save(csFriendGroupUser);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(CsFriendGroupUser csFriendGroupUser){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == csFriendGroupUser || StringUtils.isBlank(csFriendGroupUser.getId())){
            return baseResp.paramsError();
        }
        int r = csFriendGroupUserService.updateSelective(csFriendGroupUser);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = csFriendGroupUserService.delete(id);
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
            int r = csFriendGroupUserService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
