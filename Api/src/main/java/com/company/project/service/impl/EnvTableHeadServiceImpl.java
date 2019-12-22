package com.company.project.service.impl;

import com.company.project.dao.EnvTableHeadMapper;
import com.company.project.model.EnvTableHead;
import com.company.project.service.EnvTableHeadService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2019/12/18.
 */
@Service
@Transactional
public class EnvTableHeadServiceImpl extends AbstractService<EnvTableHead> implements EnvTableHeadService {
    @Resource
    private EnvTableHeadMapper envTableHeadMapper;

}
