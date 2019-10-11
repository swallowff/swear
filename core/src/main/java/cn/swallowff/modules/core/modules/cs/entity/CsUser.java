package cn.swallowff.modules.core.modules.cs.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;
import java.lang.Boolean;

/**
 * @author swallowff
 * @date 2019/07/21
 */
public class CsUser extends BaseEntity implements Serializable {

     private String sysUid;    //系统用户id
     private String avatar;    //头像
     private String userName;    //登录用户名
     private String nickName;    //昵称
     private String password;    //登录密码
     private String telephone;    //手机号
     private String email;    //邮箱
     private String sign;    //签名
     private Boolean isOnline;    //是否在线
     private String lastLoginIp;    //上次登录ip
     private Boolean status;    //用户状态: 启用 停用

     public String getSysUid(){
        return sysUid;
     }

     public void setSysUid(String sysUid) {
        this.sysUid = sysUid;
     }

     public String getAvatar(){
        return avatar;
     }

     public void setAvatar(String avatar) {
        this.avatar = avatar;
     }

     public String getUserName(){
        return userName;
     }

     public void setUserName(String userName) {
        this.userName = userName;
     }

     public String getNickName(){
        return nickName;
     }

     public void setNickName(String nickName) {
        this.nickName = nickName;
     }

     public String getPassword(){
        return password;
     }

     public void setPassword(String password) {
        this.password = password;
     }

     public String getTelephone(){
        return telephone;
     }

     public void setTelephone(String telephone) {
        this.telephone = telephone;
     }

     public String getEmail(){
        return email;
     }

     public void setEmail(String email) {
        this.email = email;
     }

     public String getSign(){
        return sign;
     }

     public void setSign(String sign) {
        this.sign = sign;
     }

     public Boolean getIsOnline(){
        return isOnline;
     }

     public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
     }

     public String getLastLoginIp(){
        return lastLoginIp;
     }

     public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
     }

     public Boolean getStatus(){
        return status;
     }

     public void setStatus(Boolean status) {
        this.status = status;
     }


}
