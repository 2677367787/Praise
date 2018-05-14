package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "impress_tag")
public class ImpressTag {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "impress_id")
    private Integer impressId;

    /**
     * 用户名编号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 印象标签
     */
    private String tag;

    /**
     * 赞同人数
     */
    private Integer count;

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
     * @return impress_id - 主键(自增长)
     */
    public Integer getImpressId() {
        return impressId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param impressId 主键(自增长)
     */
    public void setImpressId(Integer impressId) {
        this.impressId = impressId;
    }

    /**
     * 获取用户名编号
     *
     * @return user_name - 用户名编号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名编号
     *
     * @param userName 用户名编号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取印象标签
     *
     * @return tag - 印象标签
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置印象标签
     *
     * @param tag 印象标签
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取赞同人数
     *
     * @return count - 赞同人数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置赞同人数
     *
     * @param count 赞同人数
     */
    public void setCount(Integer count) {
        this.count = count;
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