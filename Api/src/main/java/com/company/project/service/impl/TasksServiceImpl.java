package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.TasksMapper;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Tasks;
import com.company.project.service.TasksService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * @author tang zhi
 * @date 2018/05/31
 */
@Service
public class TasksServiceImpl extends AbstractService<Tasks> implements TasksService {
    @Resource
    private TasksMapper tasksMapper;


    @Override
    public List<Tasks> findAll(PraiseListQueryDTO param) {
        return tasksMapper.findAll(param);
    }

	/**
	 * 查询最近3个未被领取的任务
	 * @return List<Tasks>集合
	 */
	@Override
	public List<Tasks> getNotCloseTop3() {
		return tasksMapper.getNotCloseTop3();
	}
}
