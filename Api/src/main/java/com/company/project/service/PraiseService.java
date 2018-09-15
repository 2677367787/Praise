package com.company.project.service;
import com.company.project.dto.*;
import com.company.project.model.Praise;

import java.util.HashMap;
import java.util.List;

import com.company.project.core.Service;


/**
 * Created by tang zhi on 2018/04/18.
 */
public interface PraiseService extends Service<Praise> {
	PraiseCountDTO getPraiseCountInfo(PraiseListQueryDTO praise);
	List<Praise> getPraiseList(PraiseListQueryDTO praise);
	List<PraiseListDTO> getPraiseDetail(PraiseListQueryDTO praise);
	List<PieChartDTO> getPraisePieChartData(PraiseListQueryDTO praise);

	/**
	 * 获取本周期获赞前3的人员
	 * @param queryParam 查询参数
	 * @return 集合对象
	 */
	List<Praise> getPraiseTop3(QueryParam queryParam);

	/**
	 * 查询你可能想点赞的人
	 * @param queryParam 参数
	 * @return 你可能想点赞的人
	 */
	List<HashMap<String,String>> getProbablyPraise(QueryParam queryParam);

	/**
	 * 查询给 userName 赞最多的3个人和给 userName 点赞最多的3个人
	 * @param userName 用户名
	 * @return 人员列表
	 */
	List<PieChartDTO> selectPraiseUserToEmail(String userName);

	/**
	 * 查询我点过的赞
	 * @param queryParam 查询参数
	 * @return 点赞列表
	 */
	List<Praise> getPraiseToList(QueryParam queryParam);

	/**
	 * 点赞
	 * @param praise 点赞实体
	 */
	void Add(Praise praise);

	/**
	 * 获得最近的3个点赞信息,需要未发送邮件的
	 * @param userName 用户名
	 * @return 点赞列表
	 */
	List<Praise> getRecentTop3(String userName);
}
