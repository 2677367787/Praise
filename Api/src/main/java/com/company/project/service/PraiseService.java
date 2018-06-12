package com.company.project.service;
import com.company.project.model.Praise;

import java.util.List;

import com.company.project.core.Service;
import com.company.project.dto.PieChartDTO;
import com.company.project.dto.PraiseCountDTO;
import com.company.project.dto.PraiseListQueryDTO;


/**
 * Created by tang zhi on 2018/04/18.
 */
public interface PraiseService extends Service<Praise> {
	PraiseCountDTO getPraiseCountInfo(PraiseListQueryDTO praise);
	List<Praise> getPraiseList(PraiseListQueryDTO praise);
	List<Praise> getPraiseDetail(PraiseListQueryDTO praise);
	List<PieChartDTO> getPraisePieChartData(PraiseListQueryDTO praise);
}
