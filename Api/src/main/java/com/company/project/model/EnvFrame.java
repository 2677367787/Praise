package com.company.project.model;

import javax.persistence.*;

@Table(name = "env_frame")
public class EnvFrame {
    /**
     * 主键(自增长)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标签表
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 级别名称
     */
    @Column(name = "level_name")
    private String levelName;

    /**
     * 名称
     */
    private String label;

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
     * 获取标签表
     *
     * @return parent_id - 标签表
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置标签表
     *
     * @param parentId 标签表
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取级别
     *
     * @return level - 级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置级别
     *
     * @param level 级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取级别名称
     *
     * @return level_name - 级别名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 设置级别名称
     *
     * @param levelName 级别名称
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * 获取名称
     *
     * @return label - 名称
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置名称
     *
     * @param label 名称
     */
    public void setLabel(String label) {
        this.label = label;
    }
}