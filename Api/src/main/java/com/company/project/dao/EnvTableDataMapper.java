package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.dto.env.TableStruct;
import com.company.project.model.EnvTableData;

import java.util.List;

public interface EnvTableDataMapper extends Mapper<EnvTableData> {

    /**
     * 查询所有拼接好的表格数据
     * @return List<TableStruct>
     */
    List<TableStruct> findAllTableData();

    /**
     * 通过条件更新
     * @param data 条件
     */
    void updateByEntity(EnvTableData data);
}