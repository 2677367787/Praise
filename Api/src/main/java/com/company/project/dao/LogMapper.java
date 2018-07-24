package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.dto.QueryParam;
import com.company.project.model.Log;

import java.util.List;

public interface LogMapper extends Mapper<Log> {
	/**
	 * 返回前10条数据
	 * @param queryParam 条件
	 * @return List<Log>集合
	 */
	List<Log> findRecordTopTen(QueryParam queryParam);
}