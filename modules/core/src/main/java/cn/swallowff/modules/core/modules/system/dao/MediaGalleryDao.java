package cn.swallowff.modules.core.modules.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.modules.system.entity.MediaGallery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author swallowff
 * @create 2019/07/28
 */
@Repository
@Mapper
public interface MediaGalleryDao extends CrudDao<MediaGallery> {

}