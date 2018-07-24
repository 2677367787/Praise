package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.dto.QueryParam;
import com.company.project.model.Log;

import java.util.List;


/**
 *
 * @author tang zhi
 * @date 2018/06/21
 */
public interface LogService extends Service<Log> {

	/**
	 * 返回前10条数据
	 * @param queryParam 条件
	 * @return List<Log>集合
	 */
	List<Log> findRecordTopTen(QueryParam queryParam);
}
