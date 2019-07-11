package cn.swallowff.web.cms.service;

import cn.swallowff.modules.core.cmomon.service.CrudService;
import cn.swallowff.web.cms.dao.ArticleDao;
import cn.swallowff.web.cms.entity.Article;
import org.springframework.stereotype.Service;

@Service
public class ArticleService extends CrudService<ArticleDao, Article> {

}
