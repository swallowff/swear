package cn.swallowff.modules.core.system.controller;

import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.modules.core.system.entity.ImageGallery;
import cn.swallowff.modules.core.system.service.ImageGalleryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shenyu
 * @create 2019/6/30
 */
@Controller
@RequestMapping(value = "${swear.path.admin}/sys/imageGallery")
public class ImageGalleryController extends BaseController {

    @Autowired
    private ImageGalleryService imageGalleryService;

    @RequestMapping(value = "list.html")
    @Permission(value = "sys-imageGallery-list")
    public String listHtml(){
        return "/admin/pages/system/imageGallery/imageGallery-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/admin/pages/system/imageGallery/imageGallery-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        ImageGallery imageGallery = imageGalleryService.selectById(id);
        model.addAttribute("imageGallery",imageGallery);
        return "/admin/pages/system/imageGallery/imageGallery-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(ImageGallery imageGallery){
        PageResp<ImageGallery> pageResp = imageGalleryService.findPage(imageGallery);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(ImageGallery imageGallery){
        BaseResp baseResp = BaseResp.newSuccess();
        imageGalleryService.save(imageGallery);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(ImageGallery imageGallery){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == imageGallery || StringUtils.isBlank(imageGallery.getId())){
            return baseResp.paramsError();
        }
        int r = imageGalleryService.updateSelective(imageGallery);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = imageGalleryService.delete(id);
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
            int r = imageGalleryService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
