package cn.swallowff.admin.modules.system.dao;

import cn.swallowff.admin.modules.system.entity.MediaGallery;
import cn.swallowff.admin.cmomon.dao.CrudDao;
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