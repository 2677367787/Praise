package com.company.project.service.impl;

import com.company.project.dao.EnvTableDataMapper;
import com.company.project.dto.env.TableStruct;
import com.company.project.model.EnvTableData;
import com.company.project.service.EnvTableDataService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by tang zhi on 2019/12/18.
 */
@Service
@Transactional
public class EnvTableDataServiceImpl extends AbstractService<EnvTableData> implements EnvTableDataService {
    @Resource
    private EnvTableDataMapper envTableDataMapper;

    @Override
    public List<TableStruct> findAllTableData() {
        return envTableDataMapper.findAllTableData();
    }

    @Override
    public void updateByCondition(EnvTableData data) {
        envTableDataMapper.updateByEntity(data);
    }
}
