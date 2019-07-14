package cn.swallowff.modules.core.system.dao;

import cn.swallowff.modules.core.cmomon.dao.CrudDao;
import cn.swallowff.modules.core.system.entity.ImageGallery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ImageGalleryDao extends CrudDao<ImageGallery> {

}