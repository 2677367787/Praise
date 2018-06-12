package com.company.project.service;
import com.company.project.core.Service;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Tasks;

import java.util.List;


/**
 * Created by tang zhi on 2018/05/31.
 */
public interface TasksService extends Service<Tasks> {

	/**
	 * 通过列表查询所有数据
	 * @param param 参数
	 * @return 返回 List<Tasks>
	 */
	@Override
	List<Tasks> findAll(PraiseListQueryDTO param);
}
