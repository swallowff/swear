package cn.swallowff.admin.modules.system.wrapper.mappings;

import cn.swallowff.admin.modules.system.dto.DtreeNode;
import cn.swallowff.admin.modules.system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapping {

    RoleMapping ROLE_MAPPING = Mappers.getMapper(RoleMapping.class);

    @Mapping(source = "pid", target = "parentId")
    @Mapping(source = "name", target = "title")
    @Mapping(target = "checkArr", defaultValue = "0")
    DtreeNode toDtreeNode(Role role);

}
