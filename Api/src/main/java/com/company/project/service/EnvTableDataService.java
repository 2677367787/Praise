package com.company.project.service;
import com.company.project.dto.env.TableStruct;
import com.company.project.model.EnvTableData;
import com.company.project.core.Service;

import java.util.List;


/**
 *
 * @author tang zhi
 * @date 2019/12/18
 */
public interface EnvTableDataService extends Service<EnvTableData> {

    /**
     * 查询所有拼接好的表格数据
     * @return List<TableStruct>
     */
    List<TableStruct> findAllTableData();

    /**
     * 通过条件更新
     * @param data 条件
     */
    void updateByCondition(EnvTableData data);
}
