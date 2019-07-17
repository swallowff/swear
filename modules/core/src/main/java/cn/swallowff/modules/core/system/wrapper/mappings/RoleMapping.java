package cn.swallowff.modules.core.system.wrapper.mappings;

import cn.swallowff.modules.core.system.dto.DtreeNode;
import cn.swallowff.modules.core.system.entity.Role;
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
