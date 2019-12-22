package com.company.project.service.impl;

import com.company.project.dao.EnvTableMapper;
import com.company.project.model.EnvTable;
import com.company.project.service.EnvTableService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2019/12/01.
 */
@Service
@Transactional
public class EnvTableServiceImpl extends AbstractService<EnvTable> implements EnvTableService {
    @Resource
    private EnvTableMapper envTableMapper;

}
