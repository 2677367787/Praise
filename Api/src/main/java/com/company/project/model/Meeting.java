package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Meeting {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "meeting_id")
    private Integer meetingId;

    private Integer tally;

    /**
     * 开始时间
     */
    private Date start;

    /**
     * 结束时间
     */
    private Date end;

    /**
     * 内容
     */
    private String theme;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否发送邮件
     */
    @Column(name = "is_send_email")
    private Boolean isSendEmail;

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
     * @return meeting_id - 主键(自增长)
     */
    public Integer getMeetingId() {
        return meetingId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param meetingId 主键(自增长)
     */
    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    /**
     * @return tally
     */
    public Integer getTally() {
        return tally;
    }

    /**
     * @param tally
     */
    public void setTally(Integer tally) {
        this.tally = tally;
    }

    /**
     * 获取开始时间
     *
     * @return start - 开始时间
     */
    public Date getStart() {
        return start;
    }

    /**
     * 设置开始时间
     *
     * @param start 开始时间
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * 获取结束时间
     *
     * @return end - 结束时间
     */
    public Date getEnd() {
        return end;
    }

    /**
     * 设置结束时间
     *
     * @param end 结束时间
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * 获取内容
     *
     * @return theme - 内容
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 设置内容
     *
     * @param theme 内容
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取是否发送邮件
     *
     * @return is_send_emai - 是否发送邮件
     */
    public Boolean getIsSendEmail() {
        return isSendEmail;
    }

    /**
     * 设置是否发送邮件
     *
     * @param isSendEmai 是否发送邮件
     */
    public void setIsSendEmail(Boolean isSendEmai) {
        this.isSendEmail = isSendEmai;
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