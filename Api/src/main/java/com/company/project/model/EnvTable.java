package com.company.project.model;

import javax.persistence.*;

@Table(name = "env_table")
public class EnvTable {
    /**
     * 主键(自增长)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 表格名称
     */
    @Column(name = "table_name")
    private String tableName;

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
     * 获取表格名称
     *
     * @return table_name - 表格名称
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置表格名称
     *
     * @param tableName 表格名称
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}