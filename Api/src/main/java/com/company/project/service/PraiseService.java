package com.company.project.service;
import com.company.project.model.Praise;

import java.util.List;

import com.company.project.core.Service;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.dto.PraiseTitleDTO;


/**
 * Created by tang zhi on 2018/04/18.
 */
public interface PraiseService extends Service<Praise> {
	List<PraiseTitleDTO> getPraiseCountInfo();
	List<Praise> getPraiseList(PraiseListQueryDTO praise);
	List<Praise> getPraiseDetail(PraiseListQueryDTO praise);
}
