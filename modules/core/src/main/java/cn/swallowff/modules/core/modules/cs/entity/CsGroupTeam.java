package cn.swallowff.modules.core.modules.cs.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;

/**
 * @author swallowff
 * @date 2019/07/21
 */
public class CsGroupTeam extends BaseEntity implements Serializable {

     private String gtName;    //群组名称
     private String avatar;    //群组头像
     private String notice;    //群公告

     public String getGtName(){
        return gtName;
     }

     public void setGtName(String gtName) {
        this.gtName = gtName;
     }

     public String getAvatar(){
        return avatar;
     }

     public void setAvatar(String avatar) {
        this.avatar = avatar;
     }

     public String getNotice(){
        return notice;
     }

     public void setNotice(String notice) {
        this.notice = notice;
     }


}
