package cn.swallowff.modules.core.system.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;

/**
 * @author Administrator
 * @description
 * @create 2019/7/2
 */
public class ImageGallery extends BaseEntity {
    private String originName;
    private String imgName;
    private String imgUrl;
    private String thumbnail;
    private String imgFormat;
    private Long size;

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImgFormat() {
        return imgFormat;
    }

    public void setImgFormat(String imgFormat) {
        this.imgFormat = imgFormat;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
