package cn.swallowff.modules.core.modules.cs.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;

/**
 * @author swallowff
 * @date 2019/07/23
 */
public class CsUserContactGroup extends BaseEntity implements Serializable {

     private String uid;    //聊天系统用户id
     private String groupName;    //分组名称

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGroupName(){
        return groupName;
     }

     public void setGroupName(String groupName) {
        this.groupName = groupName;
     }


}
