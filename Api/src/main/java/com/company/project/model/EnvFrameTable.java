package com.company.project.model;

import javax.persistence.*;

@Table(name = "env_frame_table")
public class EnvFrameTable {
    /**
     * 主键(自增长)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 面板ID
     */
    @Column(name = "panel_id")
    private Integer panelId;

    /**
     * 表格ID
     */
    @Column(name = "table_id")
    private Integer tableId;

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
     * 获取面板ID
     *
     * @return panel_id - 面板ID
     */
    public Integer getPanelId() {
        return panelId;
    }

    /**
     * 设置面板ID
     *
     * @param panelId 面板ID
     */
    public void setPanelId(Integer panelId) {
        this.panelId = panelId;
    }

    /**
     * 获取表格ID
     *
     * @return table_id - 表格ID
     */
    public Integer getTableId() {
        return tableId;
    }

    /**
     * 设置表格ID
     *
     * @param tableId 表格ID
     */
    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
}