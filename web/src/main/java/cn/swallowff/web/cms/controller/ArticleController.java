package cn.swallowff.web.cms.controller;

import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.cmomon.controller.BaseController;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.cmomon.resp.LayPageResp;
import cn.swallowff.modules.core.cmomon.resp.PageResp;
import cn.swallowff.web.cms.entity.Article;
import cn.swallowff.web.cms.service.ArticleService;
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
@RequestMapping(value = "${swear.path.admin}/cms/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "list.html")
    @Permission(value = "cms-article-list")
    public String listHtml(){
        return "/pages/modules/cms/article/article-list";
    }

    @RequestMapping(value = "add.html")
    public String addHtml(){
        return "/pages/modules/cms/article/article-add";
    }

    @RequestMapping(value = "edit.html")
    public String editHtml(@RequestParam("id") String id, Model model){
        Article article = articleService.selectById(id);
        model.addAttribute("article",article);
        return "/pages/modules/cms/article/article-edit";
    }

    @RequestMapping(value = "list.ajax")
    @ResponseBody
    public Object ajaxList(Article article){
        PageResp<Article> pageResp = articleService.findPage(article);
        LayPageResp layPageResp = new LayPageResp(pageResp.getDataList(),pageResp.getTotalRows());
        return layPageResp;
    }

    @RequestMapping(value = "add.ajax")
    @ResponseBody
    public BaseResp add(Article article){
        BaseResp baseResp = BaseResp.newSuccess();
        articleService.save(article);
        return baseResp;
    }

    @RequestMapping(value = "edit.ajax")
    @ResponseBody
    public BaseResp edit(Article article){
        BaseResp baseResp = BaseResp.newSuccess();
        if (null == article || StringUtils.isBlank(article.getId())){
            return baseResp.paramsError();
        }
        int r = articleService.updateSelective(article);
        if (r == 1){
            return baseResp;
        }else return baseResp.putError();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public BaseResp delete(String id){
        BaseResp baseResp = BaseResp.newSuccess();
        int r = articleService.delete(id);
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
            int r = articleService.delete(id);
            if (r == 1){
                count ++;
            }
        }
        if (count == ids.length){
            return baseResp;
        }else return baseResp.putError();
    }
}
