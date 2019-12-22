package com.company.project.model;

import javax.persistence.*;

@Table(name = "env_table_data")
public class EnvTableData {
    /**
     * 主键(自增长)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * title头Id
     */
    @Column(name = "head_id")
    private Integer headId;

    /**
     * 行id
     */
    @Column(name = "row_id")
    private Long rowId;

    /**
     * 内容
     */
    private String content;

    /**
     * tip内容
     */
    private String tips;

    /**
     * 获取主键(自增长)
     *
     * @return id - 主键(自增长)
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键(自增长)
     *
     * @param id 主键(自增长)
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取title头Id
     *
     * @return head_id - title头Id
     */
    public Integer getHeadId() {
        return headId;
    }

    /**
     * 设置title头Id
     *
     * @param headId title头Id
     */
    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    /**
     * 获取行id
     *
     * @return row_id - 行id
     */
    public Long getRowId() {
        return rowId;
    }

    /**
     * 设置行id
     *
     * @param rowId 行id
     */
    public void setRowId(Long rowId) {
        this.rowId = rowId;
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
     * 获取tip内容
     *
     * @return tips - tip内容
     */
    public String getTips() {
        return tips;
    }

    /**
     * 设置tip内容
     *
     * @param tips tip内容
     */
    public void setTips(String tips) {
        this.tips = tips;
    }
}