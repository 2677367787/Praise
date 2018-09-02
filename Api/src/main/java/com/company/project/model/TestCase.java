package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "test_case")
public class TestCase {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "case_id")
    private Integer caseId;

    /**
     * 需求内容
     */
    private String content;

    /**
     * BUG 级别 10高 20中 30低级
     */
    private Integer level;

    /**
     * 状态 10 待接受,20待验证 ,30打回, 40关闭
     */
    private Integer status;

    /**
     * 当前处理人
     */
    @Column(name = "handler_by")
    private String handlerBy;

    /**
     * 接受时间
     */
    @Column(name = "handler_date")
    private Date handlerDate;

    /**
     * 打回次数
     */
    @Column(name = "repulse_count")
    private Integer repulseCount;

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
     * @return case_id - 主键(自增长)
     */
    public Integer getCaseId() {
        return caseId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param caseId 主键(自增长)
     */
    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    /**
     * 获取需求内容
     *
     * @return content - 需求内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置需求内容
     *
     * @param content 需求内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取BUG 级别 10高 20中 30低级
     *
     * @return level - BUG 级别 10高 20中 30低级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置BUG 级别 10高 20中 30低级
     *
     * @param level BUG 级别 10高 20中 30低级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取状态 10 待接受,20待验证 ,30打回, 40关闭
     *
     * @return status - 状态 10 待接受,20待验证 ,30打回, 40关闭
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 10 待接受,20待验证 ,30打回, 40关闭
     *
     * @param status 状态 10 待接受,20待验证 ,30打回, 40关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取当前处理人
     *
     * @return handler_by - 当前处理人
     */
    public String getHandlerBy() {
        return handlerBy;
    }

    /**
     * 设置当前处理人
     *
     * @param handlerBy 当前处理人
     */
    public void setHandlerBy(String handlerBy) {
        this.handlerBy = handlerBy;
    }

    /**
     * 获取接受时间
     *
     * @return handler_date - 接受时间
     */
    public Date getHandlerDate() {
        return handlerDate;
    }

    /**
     * 设置接受时间
     *
     * @param handlerDate 接受时间
     */
    public void setHandlerDate(Date handlerDate) {
        this.handlerDate = handlerDate;
    }

    /**
     * 获取打回次数
     *
     * @return repulse_count - 打回次数
     */
    public Integer getRepulseCount() {
        return repulseCount;
    }

    /**
     * 设置打回次数
     *
     * @param repulseCount 打回次数
     */
    public void setRepulseCount(Integer repulseCount) {
        this.repulseCount = repulseCount;
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