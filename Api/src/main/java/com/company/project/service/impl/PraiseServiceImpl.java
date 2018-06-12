package com.company.project.service.impl;

import com.company.project.dao.PraiseMapper;
import com.company.project.dto.PieChartDTO;
import com.company.project.dto.PraiseCountDTO;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Praise;
import com.company.project.service.PraiseService;
import com.company.project.core.AbstractService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;


/**
 *
 * @author tang zhi
 * @date 2018/04/18
 */
@Service
@Transactional
public class PraiseServiceImpl extends AbstractService<Praise> implements PraiseService {
    @Resource
    private PraiseMapper praiseMapper;
    
    @Override
	public PraiseCountDTO getPraiseCountInfo(PraiseListQueryDTO praise){
    	return praiseMapper.getPraiseCountInfo(praise);
    }
    
    @Override
	public List<Praise> getPraiseList(PraiseListQueryDTO praise){
    	return praiseMapper.getPraiseList(praise);
    }
    
    @Override
	public List<Praise> getPraiseDetail(PraiseListQueryDTO praise){
    	return praiseMapper.getPraiseDetail(praise);
    }

	@Override
	public List<PieChartDTO> getPraisePieChartData(PraiseListQueryDTO praise) {
		if(StringUtils.isNotBlank(praise.getPraiseTo())) {
			return praiseMapper.getPraiseToPieChartData(praise);
		}else {
			return praiseMapper.getPraiseFromPieChartData(praise);
		}
	}
}
