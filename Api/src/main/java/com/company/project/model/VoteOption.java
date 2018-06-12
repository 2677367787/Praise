package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "vote_option")
public class VoteOption {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "option_id")
    private Integer optionId;

    /**
     * 主表ID
     */
    @Column(name = "vote_id")
    private Integer voteId;

    /**
     * 选项
     */
    @Column(name = "option_text")
    private String optionText;

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

    @Transient
    private List<VoteOptionDetail> voteOptionDetails;

    /**
     * 获取主键(自增长)
     *
     * @return option_id - 主键(自增长)
     */
    public Integer getOptionId() {
        return optionId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param optionId 主键(自增长)
     */
    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    /**
     * 获取主表ID
     *
     * @return vote_id - 主表ID
     */
    public Integer getVoteId() {
        return voteId;
    }

    /**
     * 设置主表ID
     *
     * @param voteId 主表ID
     */
    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    /**
     * 获取选项
     *
     * @return option_text - 选项
     */
    public String getOptionText() {
        return optionText;
    }

    /**
     * 设置选项
     *
     * @param optionText 选项
     */
    public void setOptionText(String optionText) {
        this.optionText = optionText;
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

	public List<VoteOptionDetail> getVoteOptionDetail() {
		return voteOptionDetails;
	}

	public void setVoteOptionDetail(List<VoteOptionDetail> voteOptionDetails) {
		this.voteOptionDetails = voteOptionDetails;
	}
}