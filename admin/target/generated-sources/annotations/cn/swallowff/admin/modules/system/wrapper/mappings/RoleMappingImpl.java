package cn.swallowff.admin.modules.system.wrapper.mappings;

import cn.swallowff.admin.modules.system.dto.DtreeNode;
import cn.swallowff.admin.modules.system.entity.Role;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-04T20:23:46+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_74 (Oracle Corporation)"
)
public class RoleMappingImpl implements RoleMapping {

    @Override
    public DtreeNode toDtreeNode(Role role) {
        if ( role == null ) {
            return null;
        }

        DtreeNode dtreeNode = new DtreeNode();

        dtreeNode.setTitle( role.getName() );
        dtreeNode.setParentId( role.getPid() );
        dtreeNode.setId( role.getId() );

        return dtreeNode;
    }
}
