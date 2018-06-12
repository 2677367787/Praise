package com.company.project.service.impl;

import com.company.project.dao.VoteOptionMapper;
import com.company.project.model.VoteOption;
import com.company.project.service.VoteOptionService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2018/06/09.
 */
@Service
@Transactional
public class VoteOptionServiceImpl extends AbstractService<VoteOption> implements VoteOptionService {
    @Resource
    private VoteOptionMapper voteOptionMapper;

}
