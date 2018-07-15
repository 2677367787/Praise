package com.company.project.service;
import com.company.project.dto.*;
import com.company.project.model.Praise;

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
}
