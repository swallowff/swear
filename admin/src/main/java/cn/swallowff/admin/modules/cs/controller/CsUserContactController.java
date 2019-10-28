package cn.swallowff.admin.modules.cs.controller;

import cn.swallowff.admin.cmomon.resp.LayPageResp;
import cn.swallowff.admin.cmomon.resp.PageResp;
import cn.swallowff.admin.cmomon.annotion.Permission;
import cn.swallowff.admin.cmomon.controller.BaseController;
import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.modules.cs.entity.CsUserContact;
import cn.swallowff.admin.modules.cs.service.CsUserContactService;
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
@RequestMapping(value = "${swear.path.admin}/cs/csUserContact")
public class CsUserContactController extends BaseController {

    @Autowired
    private CsUserContactService csUserContactService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cs-csUserContact-list")
    public String listHtml(){
        return "/pages/admin/cs/csUserContact/csUserContact-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/admin/cs/csUserContact/csUserContact-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        CsUserContact csUserContact = csUserContactService.selectById(id);
        model.addAttribute("csUserContact",csUserContact);
        return "/pages/admin/cs/csUserContact/csUserContact-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(CsUserContact csUserContact){
        if (csUserContact.getPage() == -1){
            return BaseResp.newSuccess().setData(csUserContactService.findList(csUserContact));
        }
        PageResp<CsUserContact> pageResp = csUserContactService.findPage(csUserContact);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(CsUserContact csUserContact){
        BaseResp baseResp = BaseResp.newSuccess();
        csUserContactService.save(csUserContact);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(CsUserContact csUserContact){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == csUserContact || StringUtils.isBlank(csUserContact.getId())){
            return baseResp.paramsError();
        }
        int r = csUserContactService.updateSelective(csUserContact);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = csUserContactService.delete(id);
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
            int r = csUserContactService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
