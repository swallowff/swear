package cn.swallowff.modules.core.modules.cs.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;

/**
 * @author swallowff
 * @date 2019/07/23
 */
public class CsUserContact extends BaseEntity implements Serializable {

     private String uid;    //用户uid
     private String groupId;    //联系人分组id
     private String cuid;    //联系人uid

     public String getUid(){
        return uid;
     }

     public void setUid(String uid) {
        this.uid = uid;
     }

     public String getGroupId(){
        return groupId;
     }

     public void setGroupId(String groupId) {
        this.groupId = groupId;
     }

     public String getCuid(){
        return cuid;
     }

     public void setCuid(String cuid) {
        this.cuid = cuid;
     }


}
