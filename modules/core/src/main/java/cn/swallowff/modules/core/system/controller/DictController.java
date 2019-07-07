package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.entity.Dict;
import cn.swallowff.modules.core.system.service.DictService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shenyu
 * @create 2019/6/30
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @RequestMapping(value = "list.html")
    @Permission(roleCode = "front")
    public String listHtml(){
        return "admin/pages/system/dict/dict-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "admin/pages/system/dict/dict-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        Dict dict = dictService.selectById(id);
        model.addAttribute("dict",dict);
        return "admin/pages/system/dict/dict-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(Dict dict){
        PageResp<Dict> pageResp = dictService.findPage(dict);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(Dict dict){
        BaseResp baseResp = BaseResp.newSuccess();
        Dict existDict = dictService.selectByCodeAndVal(dict.getCode(),dict.getVal());
        if (null != existDict){
            return baseResp.putError("字典已存在");
        }
        dictService.save(dict);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(Dict dict){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == dict || StringUtils.isBlank(dict.getId())){
            return baseResp.paramsError();
        }
        int r = dictService.updateSelective(dict);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = dictService.delete(id);
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
            int r = dictService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
