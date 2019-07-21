package cn.swallowff.modules.core.modules.cs.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;

/**
 * @author swallowff
 * @date 2019/07/21
 */
public class CsFriendGroup extends BaseEntity implements Serializable {

     private String csUid;    //聊天系统用户id
     private String groupName;    //分组名称

     public String getCsUid(){
        return csUid;
     }

     public void setCsUid(String csUid) {
        this.csUid = csUid;
     }

     public String getGroupName(){
        return groupName;
     }

     public void setGroupName(String groupName) {
        this.groupName = groupName;
     }


}
