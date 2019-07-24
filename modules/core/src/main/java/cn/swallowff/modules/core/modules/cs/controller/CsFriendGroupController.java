package cn.swallowff.modules.core.modules.cs.controller;

import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.modules.cs.entity.CsFriendGroup;
import cn.swallowff.modules.core.modules.cs.service.CsFriendGroupService;
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
@RequestMapping(value = "${swear.path.admin}/cs/csFriendGroup")
@Deprecated
public class CsFriendGroupController extends BaseController {

    @Autowired
    private CsFriendGroupService csFriendGroupService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cs-csFriendGroup-list")
    public String listHtml(){
        return "/pages/admin/cs/csFriendGroup/csFriendGroup-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/cs/csFriendGroup/csFriendGroup-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        CsFriendGroup csFriendGroup = csFriendGroupService.selectById(id);
        model.addAttribute("csFriendGroup",csFriendGroup);
        return "/pages/admin/cs/csFriendGroup/csFriendGroup-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(CsFriendGroup csFriendGroup){
        PageResp<CsFriendGroup> pageResp = csFriendGroupService.findPage(csFriendGroup);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(CsFriendGroup csFriendGroup){
        BaseResp baseResp = BaseResp.newSuccess();
        csFriendGroupService.save(csFriendGroup);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(CsFriendGroup csFriendGroup){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == csFriendGroup || StringUtils.isBlank(csFriendGroup.getId())){
            return baseResp.paramsError();
        }
        int r = csFriendGroupService.updateSelective(csFriendGroup);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = csFriendGroupService.delete(id);
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
            int r = csFriendGroupService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
