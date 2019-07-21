package cn.swallowff.modules.core.modules.cs.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;

/**
 * @author swallowff
 * @date 2019/07/21
 */
public class CsFriendGroupUser extends BaseEntity implements Serializable {

     private String ugId;    //用户分组id
     private String csUid;    //聊天系统用户id

     public String getUgId(){
        return ugId;
     }

     public void setUgId(String ugId) {
        this.ugId = ugId;
     }

     public String getCsUid(){
        return csUid;
     }

     public void setCsUid(String csUid) {
        this.csUid = csUid;
     }


}
