package cn.swallowff.web.cms.entity;

import cn.swallowff.modules.core.cmomon.entity.BaseEntity;
import java.io.Serializable;
import java.lang.String;
import java.lang.Integer;

/**
 * @author swallowff
 * @date 2019/07/11
 */

 public class Article extends BaseEntity implements Serializable {

     private String title;    //标题
     private String author;    //作者
     private String content;    //内容
     private Integer star;    //收藏数

     public String getTitle(){
        return title;
     }

     public void setTitle(String title) {
        this.title = title;
     }

     public String getAuthor(){
        return author;
     }

     public void setAuthor(String author) {
        this.author = author;
     }

     public String getContent(){
        return content;
     }

     public void setContent(String content) {
        this.content = content;
     }

     public Integer getStar(){
        return star;
     }

     public void setStar(Integer star) {
        this.star = star;
     }





 }
