package cn.swallowff.modules.core.modules.cs.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;

/**
 * @author swallowff
 * @date 2019/07/21
 */
public class CsGroupTeamUser extends BaseEntity implements Serializable {

     private String gtId;    //群组id
     private String csUid;    //用户id

     public String getGtId(){
        return gtId;
     }

     public void setGtId(String gtId) {
        this.gtId = gtId;
     }

     public String getCsUid(){
        return csUid;
     }

     public void setCsUid(String csUid) {
        this.csUid = csUid;
     }


}
