package com.company.project.service.impl;

import com.company.project.dao.TestCaseMapper;
import com.company.project.model.TestCase;
import com.company.project.service.TestCaseService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2018/09/02.
 */
@Service
@Transactional
public class TestCaseServiceImpl extends AbstractService<TestCase> implements TestCaseService {
    @Resource
    private TestCaseMapper testCaseMapper;

}
