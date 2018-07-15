package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Tasks;

import java.util.List;

/**
 * @author tanggzhi
 */
public interface TasksMapper extends Mapper<Tasks> {

	/**
	 * 覆盖父类查询所有数据的方法
	 * @return 集合Tasks
	 */
	@Override
	List<Tasks> findAll(PraiseListQueryDTO param);

	/**
	 * 查询最近3个未被领取的任务
	 * @return List<Tasks>集合
	 */
	List<Tasks> getNotCloseTop3();
}