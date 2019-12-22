package com.company.project.service.impl;

import com.company.project.dao.EnvFrameTableMapper;
import com.company.project.dto.env.TableStruct;
import com.company.project.model.EnvFrameTable;
import com.company.project.service.EnvFrameTableService;
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
public class EnvFrameTableServiceImpl extends AbstractService<EnvFrameTable> implements EnvFrameTableService {
    @Resource
    private EnvFrameTableMapper envFrameTableMapper;

    @Override
    public List<TableStruct> findTableFrame(EnvFrameTable frameTable) {
        return envFrameTableMapper.findTableFrame(frameTable);
    }

    @Override
    public List<TableStruct> findTableFrame2(EnvFrameTable condition) {
        return envFrameTableMapper.findTableFrame2(condition);
    }
}
