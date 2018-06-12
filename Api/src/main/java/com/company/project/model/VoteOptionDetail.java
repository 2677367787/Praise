package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "vote_option_detail")
public class VoteOptionDetail {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "option_detail_id")
    private Integer optionDetailId;

    /**
     * 主表ID
     */
    @Column(name = "option_id")
    private Integer optionId;

    /**
     * 投票人
     */
    private String voters;

    /**
     * 票数
     */
    private Integer share;

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
     * @return option_detail_id - 主键(自增长)
     */
    public Integer getOptionDetailId() {
        return optionDetailId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param optionDetailId 主键(自增长)
     */
    public void setOptionDetailId(Integer optionDetailId) {
        this.optionDetailId = optionDetailId;
    }

    /**
     * 获取主表ID
     *
     * @return option_id - 主表ID
     */
    public Integer getOptionId() {
        return optionId;
    }

    /**
     * 设置主表ID
     *
     * @param optionId 主表ID
     */
    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    /**
     * 获取投票人
     *
     * @return voters - 投票人
     */
    public String getVoters() {
        return voters;
    }

    /**
     * 设置投票人
     *
     * @param voters 投票人
     */
    public void setVoters(String voters) {
        this.voters = voters;
    }

    /**
     * 获取票数
     *
     * @return share - 票数
     */
    public Integer getShare() {
        return share;
    }

    /**
     * 设置票数
     *
     * @param share 票数
     */
    public void setShare(Integer share) {
        this.share = share;
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