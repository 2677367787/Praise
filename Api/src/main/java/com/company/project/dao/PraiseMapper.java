package com.company.project.dao;

import java.util.List;

import com.company.project.core.Mapper;
import com.company.project.dto.PieChartDTO;
import com.company.project.dto.PraiseCountDTO;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Praise;

public interface PraiseMapper extends Mapper<Praise> {
	PraiseCountDTO getPraiseCountInfo(PraiseListQueryDTO praise);
	
	List<Praise> getPraiseList(PraiseListQueryDTO praise);
	
	List<Praise> getPraiseDetail(PraiseListQueryDTO praise);

	List<PieChartDTO> getPraiseToPieChartData(PraiseListQueryDTO praise);
	
	List<PieChartDTO> getPraiseFromPieChartData(PraiseListQueryDTO praise);
}