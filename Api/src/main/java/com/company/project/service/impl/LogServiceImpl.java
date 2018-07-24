package com.company.project.service.impl;

import com.company.project.dao.LogMapper;
import com.company.project.dto.QueryParam;
import com.company.project.model.Log;
import com.company.project.service.LogService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by tang zhi on 2018/06/21.
 */
@Service
@Transactional
public class LogServiceImpl extends AbstractService<Log> implements LogService {
    @Resource
    private LogMapper logMapper;

	/**
	 * 返回前10条数据
	 * @param queryParam 条件
	 * @return List<Log>集合
	 */
	@Override
	public List<Log> findRecordTopTen(QueryParam queryParam) {
		return logMapper.findRecordTopTen(queryParam);
	}
}
