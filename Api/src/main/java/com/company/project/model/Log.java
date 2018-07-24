package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Log {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "log_id")
    private Integer logId;

    /**
     * 相关
     */
    private String relevant;

    /**
     * 来自
     */
    @Column(name = "be_from")
    private String beFrom;

    /**
     * 记录类型. 1、点赞 2、印象标签
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 最后更新人
     */
    @Column(name = "last_update_by")
    private String lastUpdateBy;

    /**
     * 最后更新时间
     */
    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * 是否有效
     */
    @Column(name = "enable_flag")
    private Integer enableFlag;

    /**
     * 获取主键(自增长)
     *
     * @return log_id - 主键(自增长)
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param logId 主键(自增长)
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * 获取相关
     *
     * @return relevant - 相关
     */
    public String getRelevant() {
        return relevant;
    }

    /**
     * 设置相关
     *
     * @param relevant 相关
     */
    public void setRelevant(String relevant) {
        this.relevant = relevant;
    }

    /**
     * 获取来自
     *
     * @return be_from - 来自
     */
    public String getBeFrom() {
        return beFrom;
    }

    /**
     * 设置来自
     *
     * @param beFrom 来自
     */
    public void setBeFrom(String beFrom) {
        this.beFrom = beFrom;
    }

    /**
     * 获取记录类型. 1、点赞 2、印象标签
     *
     * @return type - 记录类型. 1、点赞 2、印象标签
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置记录类型. 1、点赞 2、印象标签
     *
     * @param type 记录类型. 1、点赞 2、印象标签
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取最后更新人
     *
     * @return last_update_by - 最后更新人
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * 设置最后更新人
     *
     * @param lastUpdateBy 最后更新人
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * 获取最后更新时间
     *
     * @return last_update_date - 最后更新时间
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * 设置最后更新时间
     *
     * @param lastUpdateDate 最后更新时间
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * 获取是否有效
     *
     * @return enable_flag - 是否有效
     */
    public Integer getEnableFlag() {
        return enableFlag;
    }

    /**
     * 设置是否有效
     *
     * @param enableFlag 是否有效
     */
    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }
}