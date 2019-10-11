package cn.swallowff.modules.core.modules.system.wrapper.mappings;

import cn.swallowff.modules.core.modules.system.dto.DtreeNode;
import cn.swallowff.modules.core.modules.system.entity.Role;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-11T21:15:44+0800",
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
