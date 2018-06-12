package com.company.project.service;
import com.company.project.model.TasksDetail;
import com.company.project.core.Service;


/**
 * Created by tang zhi on 2018/05/31.
 */
public interface TasksDetailService extends Service<TasksDetail> {
    /**
     * 充能人数到达3,触发状态更改
     * @param tasksId
     */
    void updateStatusByTasksId(Integer tasksId);

	/**
	 * 通过主表ID删除所有明细
	 * @param tasksId
	 */
	void deleteByMainId(Integer tasksId);
}
