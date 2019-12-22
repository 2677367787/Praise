package com.company.project.service.impl;

import com.company.project.dao.EnvFrameMapper;
import com.company.project.dto.env.EnvFrameDTO;
import com.company.project.model.EnvFrame;
import com.company.project.service.EnvFrameService;
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
public class EnvFrameServiceImpl extends AbstractService<EnvFrame> implements EnvFrameService {
    @Resource
    private EnvFrameMapper envFrameMapper;

    @Override
    public List<EnvFrameDTO> findAllAndTable() {
        return envFrameMapper.findAllAndTable();
    }
}
