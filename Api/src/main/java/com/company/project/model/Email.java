package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Email {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "email_id")
    private Integer emailId;

    /**
     * 关联ID
     */
    @Column(name = "relation_id")
    private Integer relationId;

    /**
     * 发件人
     */
    private String sender;

    /**
     * 收件人
     */
    private String recipients;

    /**
     * 抄送
     */
    private String cc;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    @Column(name = "mb_text")
    private String mbText;

    /**
     * 点击
     */
    @Column(name = "click_see")
    private String clickSee;

    /**
     * 系统地址
     */
    @Column(name = "link_address")
    private String linkAddress;

    /**
     * 系统名称
     */
    @Column(name = "system_name")
    private String systemName;

    /**
     * 系统名称
     */
    private String notes;

    /**
     * 0.未发送 1.已发送
     */
    @Column(name = "is_send")
    private Integer isSend;

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
     * @return email_id - 主键(自增长)
     */
    public Integer getEmailId() {
        return emailId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param emailId 主键(自增长)
     */
    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    /**
     * 获取关联ID
     *
     * @return relation_id - 关联ID
     */
    public Integer getRelationId() {
        return relationId;
    }

    /**
     * 设置关联ID
     *
     * @param relationId 关联ID
     */
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取发件人
     *
     * @return sender - 发件人
     */
    public String getSender() {
        return sender;
    }

    /**
     * 设置发件人
     *
     * @param sender 发件人
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 获取收件人
     *
     * @return recipients - 收件人
     */
    public String getRecipients() {
        return recipients;
    }

    /**
     * 设置收件人
     *
     * @param recipients 收件人
     */
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    /**
     * 获取抄送
     *
     * @return cc - 抄送
     */
    public String getCc() {
        return cc;
    }

    /**
     * 设置抄送
     *
     * @param cc 抄送
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * 获取主题
     *
     * @return subject - 主题
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置主题
     *
     * @param subject 主题
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取内容
     *
     * @return mb_text - 内容
     */
    public String getMbText() {
        return mbText;
    }

    /**
     * 设置内容
     *
     * @param mbText 内容
     */
    public void setMbText(String mbText) {
        this.mbText = mbText;
    }

    /**
     * 获取点击
     *
     * @return click_see - 点击
     */
    public String getClickSee() {
        return clickSee;
    }

    /**
     * 设置点击
     *
     * @param clickSee 点击
     */
    public void setClickSee(String clickSee) {
        this.clickSee = clickSee;
    }

    /**
     * 获取系统地址
     *
     * @return link_address - 系统地址
     */
    public String getLinkAddress() {
        return linkAddress;
    }

    /**
     * 设置系统地址
     *
     * @param linkAddress 系统地址
     */
    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    /**
     * 获取系统名称
     *
     * @return system_name - 系统名称
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * 设置系统名称
     *
     * @param systemName 系统名称
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * 获取系统名称
     *
     * @return notes - 系统名称
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 设置系统名称
     *
     * @param notes 系统名称
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 获取0.未发送 1.已发送
     *
     * @return is_send - 0.未发送 1.已发送
     */
    public Integer getIsSend() {
        return isSend;
    }

    /**
     * 设置0.未发送 1.已发送
     *
     * @param isSend 0.未发送 1.已发送
     */
    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
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