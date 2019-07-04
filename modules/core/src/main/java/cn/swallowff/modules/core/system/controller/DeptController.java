package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.entity.Dept;
import cn.swallowff.modules.core.system.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenyu
 * @create 2019/6/30
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "list.html")
    public String listHtml(){
        return "admin/pages/system/dept/dept-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "admin/pages/system/dept/dept-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        Dept dept = deptService.selectById(id);
        model.addAttribute("dept",dept);
        return "admin/pages/system/dept/dept-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(Dept dept){
        PageResp<Dept> pageResp = deptService.findPage(dept);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "treeList.ajax")
    @ResponseBody
    public Object ajaxTree(Dept dept){
        dept.setPid("0");
        dept.setFullName("根节点");
        BaseResp baseResp = BaseResp.newSuccess();
        dept = deptService.findChildren(dept);
        List<Dept> list = new ArrayList<>();
        list.add(dept);
        return baseResp.setData(list);
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(Dept dept){
        BaseResp baseResp = BaseResp.newSuccess();
        deptService.save(dept);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(Dept dept){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == dept || StringUtils.isBlank(dept.getId())){
            return baseResp.paramsError();
        }
        int r = deptService.updateSelective(dept);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = deptService.delete(id);
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
            int r = deptService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
