package com.company.project.dao;

import java.util.List;

import com.company.project.core.Mapper;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.dto.PraiseTitleDTO;
import com.company.project.model.Praise;

public interface PraiseMapper extends Mapper<Praise> {
	List<PraiseTitleDTO> getPraiseCountInfo();
	
	List<Praise> getPraiseList(PraiseListQueryDTO praise);
	
	List<Praise> getPraiseDetail(PraiseListQueryDTO praise);
}