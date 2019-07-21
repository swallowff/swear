package cn.swallowff.modules.core.modules.cs.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;

/**
 * @author swallowff
 * @date 2019/07/21
 */
public class CsUserFriend extends BaseEntity implements Serializable {

     private String csUid;    //聊天系统用户id
     private String fuid;    //好友用户id

     public String getCsUid(){
        return csUid;
     }

     public void setCsUid(String csUid) {
        this.csUid = csUid;
     }

     public String getFuid(){
        return fuid;
     }

     public void setFuid(String fuid) {
        this.fuid = fuid;
     }


}
