package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tasks_detail")
public class TasksDetail {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "tasks_detail_id")
    private Integer tasksDetailId;

    /**
     * 任务ID
     */
    @Column(name = "tasks_id")
    private Integer tasksId;

    /**
     * 补充信息
     */
    @Column(name = "demand_content")
    private String demandContent;

    /**
     * 评分
     */
    private double evaluate;

	/**
	 * 评论结果
	 */
	private String comment;

	/**
	 * 是否评论
	 */
	private Integer isComment;
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
     * @return tasks_detail_id - 主键(自增长)
     */
    public Integer getTasksDetailId() {
        return tasksDetailId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param tasksDetailId 主键(自增长)
     */
    public void setTasksDetailId(Integer tasksDetailId) {
        this.tasksDetailId = tasksDetailId;
    }

    /**
     * 获取任务ID
     *
     * @return tasks_id - 任务ID
     */
    public Integer getTasksId() {
        return tasksId;
    }

    /**
     * 设置任务ID
     *
     * @param tasksId 任务ID
     */
    public void setTasksId(Integer tasksId) {
        this.tasksId = tasksId;
    }

    /**
     * 获取补充信息
     *
     * @return demand_content - 补充信息
     */
    public String getDemandContent() {
        return demandContent;
    }

    /**
     * 设置补充信息
     *
     * @param demandContent 补充信息
     */
    public void setDemandContent(String demandContent) {
        this.demandContent = demandContent;
    }

    /**
     * 获取评价
     *
     * @return evaluate - 评价
     */
    public double getEvaluate() {
        return evaluate;
    }

    /**
     * 设置评价
     *
     * @param evaluate 评价
     */
    public void setEvaluate(double evaluate) {
        this.evaluate = evaluate;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getIsComment() {
		return isComment;
	}

	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
}