package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

public class Tasks {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "tasks_id")
    private Integer tasksId;

    /**
     * 需求标题
     */
    @Column(name = "demand_title")
    private String demandTitle;

    /**
     * 需求内容
     */
    @Column(name = "demand_content")
    private String demandContent;

    /**
     * 状态 0待充能,1待接受,2待完成,3已完成
     */
    private Integer status;

    /**
     * 接受人
     */
    @Column(name = "accept_by")
    private String acceptBy;

    /**
     * 接受时间
     */
    @Column(name = "accept_date")
    private Date acceptDate;

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
     * 联合创建人
     */
    @Transient
    private String unionCreateBy;

	/**
	 * 发布人
	 */
	@Transient
	private String releaseBy;

    /**
     * 获取主键(自增长)
     *
     * @return tasks_id - 主键(自增长)
     */
    public Integer getTasksId() {
        return tasksId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param tasksId 主键(自增长)
     */
    public void setTasksId(Integer tasksId) {
        this.tasksId = tasksId;
    }

    /**
     * 获取需求标题
     *
     * @return demand_title - 需求标题
     */
    public String getDemandTitle() {
        return demandTitle;
    }

    /**
     * 设置需求标题
     *
     * @param demandTitle 需求标题
     */
    public void setDemandTitle(String demandTitle) {
        this.demandTitle = demandTitle;
    }

    /**
     * 获取需求内容
     *
     * @return demand_content - 需求内容
     */
    public String getDemandContent() {
        return demandContent;
    }

    /**
     * 设置需求内容
     *
     * @param demandContent 需求内容
     */
    public void setDemandContent(String demandContent) {
        this.demandContent = demandContent;
    }

    /**
     * 获取状态 0待充能,1待接受,2待完成,3已完成
     *
     * @return status - 状态 0待充能,1待接受,2待完成,3已完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0待充能,1待接受,2待完成,3已完成
     *
     * @param status 状态 0待充能,1待接受,2待完成,3已完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取接受人
     *
     * @return accept_by - 接受人
     */
    public String getAcceptBy() {
        return acceptBy;
    }

    /**
     * 设置接受人
     *
     * @param acceptBy 接受人
     */
    public void setAcceptBy(String acceptBy) {
        this.acceptBy = acceptBy;
    }

    /**
     * 获取接受时间
     *
     * @return accept_date - 接受时间
     */
    public Date getAcceptDate() {
        return acceptDate;
    }

    /**
     * 设置接受时间
     *
     * @param acceptDate 接受时间
     */
    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
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

    public String getUnionCreateBy() {
        return unionCreateBy;
    }

    public void setUnionCreateBy(String unionCreateBy) {
        this.unionCreateBy = unionCreateBy;
    }

	public String getReleaseBy() {
		return releaseBy;
	}

	public void setReleaseBy(String releaseBy) {
		this.releaseBy = releaseBy;
	}
}