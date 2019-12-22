package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.dto.env.TableStruct;
import com.company.project.model.EnvFrameTable;

import java.util.List;

public interface EnvFrameTableMapper extends Mapper<EnvFrameTable> {
    /**
     * 根据条件查询所有表格结构
     * @param frameTable EnvFrameTable 查询条件
     * @return List<EnvFrameTable>
     */
    List<TableStruct> findTableFrame(EnvFrameTable frameTable);

    /**
     * 根据条件查询所有表格结构
     * @param condition EnvFrameTable 查询条件
     * @return List<EnvFrameTable>
     */
    List<TableStruct> findTableFrame2(EnvFrameTable condition);
}