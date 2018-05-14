package com.company.project.service.impl;

import com.company.project.dao.ImpressTagMapper;
import com.company.project.model.ImpressTag;
import com.company.project.service.ImpressTagService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2018/05/13.
 */
@Service
@Transactional
public class ImpressTagServiceImpl extends AbstractService<ImpressTag> implements ImpressTagService {
    @Resource
    private ImpressTagMapper impressTagMapper;

}
