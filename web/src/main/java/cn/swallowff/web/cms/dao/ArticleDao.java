package cn.swallowff.web.cms.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.web.cms.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ArticleDao extends CrudDao<Article> {

}