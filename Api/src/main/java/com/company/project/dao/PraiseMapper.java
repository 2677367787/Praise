package com.company.project.dao;

import java.util.List;

import com.company.project.core.Mapper;
import com.company.project.dto.*;
import com.company.project.model.Praise;

public interface PraiseMapper extends Mapper<Praise> {
	PraiseCountDTO getPraiseCountInfo(PraiseListQueryDTO praise);
	
	List<Praise> getPraiseList(PraiseListQueryDTO praise);

	/**
	 * 查询点赞列表明细
	 * @param praise
	 * @return
	 */
	List<PraiseListDTO> getPraiseDetail(PraiseListQueryDTO praise);

	/**
	 * 点赞饼图数据源
	 * @param praise 查询参数
	 * @return 返回集合对象
	 */
	List<PieChartDTO> getPraiseToPieChartData(PraiseListQueryDTO praise);

	/**
	 * 获赞饼图数据源
	 * @param praise 查询参数
	 * @return 返回集合对象
	 */
	List<PieChartDTO> getPraiseFromPieChartData(PraiseListQueryDTO praise);

	/**
	 * 获取本周期获赞前3的人员
	 * @param queryParam 查询参数
	 * @return 集合对象
	 */
	List<Praise> getPraiseTop3(QueryParam queryParam);
}