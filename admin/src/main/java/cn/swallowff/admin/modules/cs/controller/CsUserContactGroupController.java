package cn.swallowff.admin.modules.cs.controller;

import cn.swallowff.admin.cmomon.resp.LayPageResp;
import cn.swallowff.admin.cmomon.resp.PageResp;
import cn.swallowff.admin.cmomon.annotion.Permission;
import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.modules.cs.entity.CsUserContactGroup;
import cn.swallowff.admin.modules.cs.service.CsUserContactGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author swallowff
 * @create 2019/07/23
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/cs/csUserContactGroup")
public class CsUserContactGroupController extends BaseController {

    @Autowired
    private CsUserContactGroupService csUserContactGroupService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cs-csUserContactGroup-list")
    public String listHtml(){
        return "/pages/admin/cs/csUserContactGroup/csUserContactGroup-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/cs/csUserContactGroup/csUserContactGroup-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        CsUserContactGroup csUserContactGroup = csUserContactGroupService.selectById(id);
        model.addAttribute("csUserContactGroup",csUserContactGroup);
        return "/pages/admin/cs/csUserContactGroup/csUserContactGroup-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(CsUserContactGroup csUserContactGroup){
        if (csUserContactGroup.getPage() == -1){
            return BaseResp.newSuccess().setData(csUserContactGroupService.findList(csUserContactGroup));
        }
        PageResp<CsUserContactGroup> pageResp = csUserContactGroupService.findPage(csUserContactGroup);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(CsUserContactGroup csUserContactGroup){
        BaseResp baseResp = BaseResp.newSuccess();
        csUserContactGroupService.save(csUserContactGroup);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(CsUserContactGroup csUserContactGroup){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == csUserContactGroup || StringUtils.isBlank(csUserContactGroup.getId())){
            return baseResp.paramsError();
        }
        int r = csUserContactGroupService.updateSelective(csUserContactGroup);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = csUserContactGroupService.delete(id);
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
            int r = csUserContactGroupService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
