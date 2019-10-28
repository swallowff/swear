package cn.swallowff.admin.modules.system.entity;

import cn.swallowff.admin.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.lang.Long;

/**
 * @author swallowff
 * @date 2019/07/28
 */
public class MediaGallery extends BaseEntity implements Serializable {

     private Integer mediaType;    //媒体类型(0:视频  1:音乐)
     private String originName;    //原始名称
     private String name;    //存储名称
     private String localPath;   //本地文件路径
     private String url;    //资源地址
     private String cover;    //视频封面图
     private String mediaFormat;    //格式
     private Long size;    //大小

     public Integer getMediaType(){
        return mediaType;
     }

     public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
     }

     public String getOriginName(){
        return originName;
     }

     public void setOriginName(String originName) {
        this.originName = originName;
     }

     public String getName(){
        return name;
     }

     public void setName(String name) {
        this.name = name;
     }

     public String getUrl(){
        return url;
     }

     public void setUrl(String url) {
        this.url = url;
     }

     public String getCover(){
        return cover;
     }

     public void setCover(String cover) {
        this.cover = cover;
     }

     public String getMediaFormat(){
        return mediaFormat;
     }

     public void setMediaFormat(String mediaFormat) {
        this.mediaFormat = mediaFormat;
     }

     public Long getSize(){
        return size;
     }

     public void setSize(Long size) {
        this.size = size;
     }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
