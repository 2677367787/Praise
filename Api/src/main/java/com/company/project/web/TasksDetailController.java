package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TasksDetail;
import com.company.project.service.TasksDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
*
 * @author tang zhi
 * @date 2018/05/31
 */
@RestController
@RequestMapping("/tasks/detail")
public class TasksDetailController extends BaseController {
    @Resource
    private TasksDetailService tasksDetailService;


    @PostMapping
    public Result add(@RequestBody TasksDetail tasksDetail) {
        tasksDetail.setCreateBy(this.getUserName());
        tasksDetail.setLastUpdateBy(this.getUserName());
        tasksDetailService.save(tasksDetail);
        // 充能人数到达3修改状态
        tasksDetailService.updateStatusByTasksId(tasksDetail.getTasksId());
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        tasksDetailService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody TasksDetail tasksDetail) {
        tasksDetailService.update(tasksDetail);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        TasksDetail tasksDetail = tasksDetailService.findById(id);
        return ResultGenerator.genSuccessResult(tasksDetail);
    }

	@GetMapping("/all/{tasksId}")
	public Result getAllTasksDetail(@PathVariable Integer tasksId) {
		Condition condition = new Condition(TasksDetail.class);
		condition.createCriteria().andEqualTo("tasksId", tasksId);
		List<TasksDetail> tasksDetail = tasksDetailService.findByCondition(condition);
		return ResultGenerator.genSuccessResult(tasksDetail);
	}

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TasksDetail> list = tasksDetailService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
