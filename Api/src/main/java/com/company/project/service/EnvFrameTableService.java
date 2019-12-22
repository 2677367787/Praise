package com.company.project.service;
import com.company.project.dto.env.TableStruct;
import com.company.project.model.EnvFrameTable;
import com.company.project.core.Service;

import java.util.List;


/**
 * 表格框架类
 * @author tang zhi
 * @date 2019/12/18
 */
public interface EnvFrameTableService extends Service<EnvFrameTable> {

    /**
     * 根据条件查询所有表格结构
     * @param condition EnvFrameTable 查询条件
     * @return List<EnvFrameTable>
     */
    List<TableStruct> findTableFrame(EnvFrameTable condition);

    /**
     * 根据条件查询所有表格结构
     * @param condition EnvFrameTable 查询条件
     * @return List<EnvFrameTable>
     */

    List<TableStruct> findTableFrame2(EnvFrameTable condition);
}
