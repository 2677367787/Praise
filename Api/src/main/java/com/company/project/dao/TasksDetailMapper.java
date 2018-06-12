package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.TasksDetail;

/**
 * @author tanggzhi
 */
public interface TasksDetailMapper extends Mapper<TasksDetail> {
    /**
     * 充能人数到达3,触发状态更改
     * @param tasksId
     * @return
     */
    void updateStatusByTasksId(Integer tasksId);

    void deleteByMainId(Integer tasksId);
}