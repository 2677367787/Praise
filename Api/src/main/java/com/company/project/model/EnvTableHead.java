package com.company.project.model;

import javax.persistence.*;

@Table(name = "env_table_head")
public class EnvTableHead {
    /**
     * 主键(自增长)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 表格ID
     */
    @Column(name = "table_id")
    private Integer tableId;

    /**
     * 字段名称
     */
    private String field;

    /**
     * 单元格名称
     */
    @Column(name = "cell_name")
    private String cellName;

    /**
     * 标签类型 1超链接 2气泡
     */
    @Column(name = "cell_type")
    private String cellType;

    /**
     * 列宽度
     */
    private String weight;

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

    /**
     * 获取字段名称
     *
     * @return field - 字段名称
     */
    public String getField() {
        return field;
    }

    /**
     * 设置字段名称
     *
     * @param field 字段名称
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * 获取单元格名称
     *
     * @return cell_name - 单元格名称
     */
    public String getCellName() {
        return cellName;
    }

    /**
     * 设置单元格名称
     *
     * @param cellName 单元格名称
     */
    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    /**
     * 获取标签类型 1超链接 2气泡
     *
     * @return cell_type - 标签类型 1超链接 2气泡
     */
    public String getCellType() {
        return cellType;
    }

    /**
     * 设置标签类型 1超链接 2气泡
     *
     * @param cellType 标签类型 1超链接 2气泡
     */
    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getWeight() {
        return weight;
    }

    public EnvTableHead setWeight(String weight) {
        this.weight = weight;
        return this;
    }
}