package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Praise {
    /**
     * 主键(自增长)
     */
    @Id
    @Column(name = "praise_id")
    private Integer praiseId;

    /**
     * 来自
     */
    @Column(name = "praise_from")
    private String praiseFrom;

    /**
     * 目标
     */
    @Column(name = "praise_to")
    private String praiseTo;

    /**
     * 内容
     */
    private String content;

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
     * 姓名+工号
     */     
    @Transient
    private String uniqueName;
    
    /**
     * 送出赞
     */         
    @Transient
    private String give;
    
    /**
     * 获得赞
     */         
    @Transient
    private String gain;

    /**
     * 工号
     */         
    @Transient
    private String username;
    /**
     * 获取主键(自增长)
     *
     * @return praise_id - 主键(自增长)
     */
    public Integer getPraiseId() {
        return praiseId;
    }

    /**
     * 设置主键(自增长)
     *
     * @param praiseId 主键(自增长)
     */
    public void setPraiseId(Integer praiseId) {
        this.praiseId = praiseId;
    }

    /**
     * 获取来自
     *
     * @return praise_from - 来自
     */
    public String getPraiseFrom() {
        return praiseFrom;
    }

    /**
     * 设置来自
     *
     * @param praiseFrom 来自
     */
    public void setPraiseFrom(String praiseFrom) {
        this.praiseFrom = praiseFrom;
    }

    /**
     * 获取目标
     *
     * @return praise_to - 目标
     */
    public String getPraiseTo() {
        return praiseTo;
    }

    /**
     * 设置目标
     *
     * @param praiseTo 目标
     */
    public void setPraiseTo(String praiseTo) {
        this.praiseTo = praiseTo;
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

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public String getGive() {
		return give;
	}

	public void setGive(String give) {
		this.give = give;
	}

	public String getGain() {
		return gain;
	}

	public void setGain(String gain) {
		this.gain = gain;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}