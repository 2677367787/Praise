package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author tanggzhi
 */
public class Vote {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "vote_id")
    @GeneratedValue(generator = "JDBC")
    private Integer voteId;

    /**
     * 主题
     */
    private String theme;

    /**
     * 补充信息
     */
    private String additional;

    /**
     * 投票类型 1.积分制,2.单选,3.多选
     */
    private Integer type;

    /**
     * 票数,单选,多选为默认为1 积分制根据输入
     */
    @Column(name = "votes_number")
    private Integer votesNumber;

    /**
     * 参与人数
     */
    @Column(name = "participants")
    private Integer participants;

    /**
     * 是否可选择自己
     */
    @Column(name = "select_myself")
    private Boolean selectMyself;

    /**
     * 最多选项
     */
    @Column(name = "most_select")
    private Integer mostSelect;

    /**
     * 最少选项
     */
    @Column(name = "least_select")
    private Integer leastSelect;

    /**
     * 关闭时间
     */
    @Column(name = "close_date")
    private Date closeDate;

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
	 * 选项类型,项目组成员或自定义
	 */
	@Transient
    private String optionType;
    /**
     * 获取主键(自增长)
     *
     * @return vote_id - 主键(自增长)
     */
    public Integer getVoteId() {
        return voteId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param voteId 主键(自增长)
     */
    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    /**
     * 获取主题
     *
     * @return theme - 主题
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 设置主题
     *
     * @param theme 主题
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * 获取补充信息
     *
     * @return additional - 补充信息
     */
    public String getAdditional() {
        return additional;
    }

    /**
     * 设置补充信息
     *
     * @param additional 补充信息
     */
    public void setAdditional(String additional) {
        this.additional = additional;
    }

    /**
     * 获取投票类型 1.积分制,2.单选,3.多选
     *
     * @return type - 投票类型 1.积分制,2.单选,3.多选
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置投票类型 1.积分制,2.单选,3.多选
     *
     * @param type 投票类型 1.积分制,2.单选,3.多选
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取票数,单选,多选为默认为1 积分制根据输入
     *
     * @return votes_number - 票数,单选,多选为默认为1 积分制根据输入
     */
    public Integer getVotesNumber() {
        return votesNumber;
    }

    /**
     * 设置票数,单选,多选为默认为1 积分制根据输入
     *
     * @param votesNumber 票数,单选,多选为默认为1 积分制根据输入
     */
    public void setVotesNumber(Integer votesNumber) {
        this.votesNumber = votesNumber;
    }

    /**
     * 获取是否可选择自己
     *
     * @return select_myself - 是否可选择自己
     */
    public Boolean getSelectMyself() {
        return selectMyself;
    }

    /**
     * 设置是否可选择自己
     *
     * @param selectMyself 是否可选择自己
     */
    public void setSelectMyself(Boolean selectMyself) {
        this.selectMyself = selectMyself;
    }

    /**
     * 获取最多选项
     *
     * @return most_select - 最多选项
     */
    public Integer getMostSelect() {
        return mostSelect;
    }

    /**
     * 设置最多选项
     *
     * @param mostSelect 最多选项
     */
    public void setMostSelect(Integer mostSelect) {
        this.mostSelect = mostSelect;
    }

    /**
     * 获取最少选项
     *
     * @return least_select - 最少选项
     */
    public Integer getLeastSelect() {
        return leastSelect;
    }

    /**
     * 设置最少选项
     *
     * @param leastSelect 最少选项
     */
    public void setLeastSelect(Integer leastSelect) {
        this.leastSelect = leastSelect;
    }

    /**
     * 获取关闭时间
     *
     * @return close_date - 关闭时间
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * 设置关闭时间
     *
     * @param closeDate 关闭时间
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * 获取是否发送邮件
     *
     * @return is_send_email - 是否发送邮件
     */
    public Boolean getIsSendEmail() {
        return isSendEmail;
    }

    /**
     * 设置是否发送邮件
     *
     * @param isSendEmail 是否发送邮件
     */
    public void setIsSendEmail(Boolean isSendEmail) {
        this.isSendEmail = isSendEmail;
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

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }
}