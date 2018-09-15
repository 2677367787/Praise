package com.company.project.service.impl;

import com.company.project.dao.PraiseMapper;
import com.company.project.dto.*;
import com.company.project.model.Praise;
import com.company.project.service.PraiseService;
import com.company.project.core.AbstractService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
	public List<PraiseListDTO> getPraiseDetail(PraiseListQueryDTO praise){
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

	/**
	 * 获取本周期获赞前3的人员
	 * @param queryParam 查询参数
	 * @return 集合对象
	 */
	@Override
	public List<Praise> getPraiseTop3(QueryParam queryParam) {
		return praiseMapper.getPraiseTop3(queryParam);
	}

	/**
	 * 查询你可能想点赞的人
	 * @param queryParam 参数
	 * @return 你可能想点赞的人
	 */
	@Override
	public List<HashMap<String,String>> getProbablyPraise(QueryParam queryParam) {
		return praiseMapper.getProbablyPraise(queryParam);
	}

	/**
	 * 查询给 userName 赞最多的3个人和给 userName 点赞最多的3个人
	 * @param userName 用户名
	 * @return 人员列表
	 */
	@Override
	public List<PieChartDTO> selectPraiseUserToEmail(String userName) {
		return praiseMapper.selectPraiseUserToEmail(userName);
	}

	/**
	 * 查询我点过的赞
	 * @param queryParam 查询参数
	 * @return 点赞列表
	 */
	@Override
	public List<Praise> getPraiseToList(QueryParam queryParam) {
		return praiseMapper.getPraiseToList(queryParam);
	}

	/**
	 * 点赞
	 * @param praise 点赞实体
	 */
	@Override
	public void Add(Praise praise) {
		praiseMapper.add(praise);
	}

	/**
	 * 获得最近的3个点赞信息,需要未发送邮件的
	 * @param userName 用户名
	 * @return 点赞列表
	 */
	@Override
	public List<Praise> getRecentTop3(String userName) {
		return praiseMapper.getRecentTop3(userName);
	}
}
