package com.company.project.service.impl;

import com.company.project.dao.TasksDetailMapper;
import com.company.project.model.TasksDetail;
import com.company.project.service.TasksDetailService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 *
 * @author tang zhi
 * @date 2018/05/31
 */
@Service
@Transactional
public class TasksDetailServiceImpl extends AbstractService<TasksDetail> implements TasksDetailService {
    @Resource
    private TasksDetailMapper tasksDetailMapper;

    @Override
    public void updateStatusByTasksId(Integer tasksId) {
        tasksDetailMapper.updateStatusByTasksId(tasksId);
    }

    @Override
    public void deleteByMainId(Integer tasksId) {
        tasksDetailMapper.deleteByMainId(tasksId);
    }
}
