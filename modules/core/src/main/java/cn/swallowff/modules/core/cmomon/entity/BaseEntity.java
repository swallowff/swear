package cn.swallowff.modules.core.cmomon.entity;

import cn.swallowff.common.idgen.IdGenerate;
import cn.swallowff.common.lang.StringUtils;
import org.omg.CORBA.INTERNAL;

import java.util.Date;

public class BaseEntity<E extends BaseEntity> {
    private String id;
    private Date createTime;
    private Date updateTime;
    private Integer version;
    private String orderBy;
    private boolean delflag;

    private Integer pageNum = 1;
    private Integer pageSize = 20;

    private boolean isNewRecord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isDelflag() {
        return delflag;
    }

    public void setDelflag(boolean delflag) {
        this.delflag = delflag;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     * @return
     */
    public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public void preInsert(){
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (!this.isNewRecord){
            this.setId(IdGenerate.nextId());
        }
        this.updateTime = new Date();
        this.createTime = this.updateTime;
    }

    public void preUpdate(){
        this.updateTime = new Date();
    }
}
