package com.company.project.service.impl;

import com.company.project.dao.PraiseMapper;
import com.company.project.dto.PraiseCountDTO;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Praise;
import com.company.project.service.PraiseService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;


/**
 * Created by tang zhi on 2018/04/18.
 */
@Service
@Transactional
public class PraiseServiceImpl extends AbstractService<Praise> implements PraiseService {
    @Resource
    private PraiseMapper praiseMapper;
    
    public PraiseCountDTO getPraiseCountInfo(PraiseListQueryDTO praise){
    	return praiseMapper.getPraiseCountInfo(praise);
    }
    
    public List<Praise> getPraiseList(PraiseListQueryDTO praise){
    	return praiseMapper.getPraiseList(praise);
    }
    
    public List<Praise> getPraiseDetail(PraiseListQueryDTO praise){
    	return praiseMapper.getPraiseDetail(praise);
    }
}
