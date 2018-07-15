package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Praise;
import com.company.project.model.Tasks;
import com.company.project.model.Vote;
import com.company.project.service.PraiseService;
import com.company.project.service.TasksDetailService;
import com.company.project.service.TasksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tang zhi
 * @date 2018/05/21
 */
@RestController
@RequestMapping("/tasks")
public class TasksController extends BaseController{
    @Resource
    private TasksService tasksService;

    @Resource
    private TasksDetailService tasksDetailService;

    @Resource
    private PraiseService praiseService;

    private static final Integer COMPLETE = 4;

    @PostMapping
    public Result add(@RequestBody Tasks tasks) {
        tasks.setCreateBy(this.getUserName());
        tasks.setLastUpdateBy(this.getUserName());
        tasksService.save(tasks);

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/receive")
    public Result receive(@RequestBody Tasks tasks){

    	Tasks task = tasksService.findById(tasks.getTasksId());
	    task.setStatus(tasks.getStatus());
    	task.setAcceptBy(this.getUserName());
    	task.setAcceptDate(new Date());
    	tasksService.update(task);

    	if(COMPLETE.equals(tasks.getStatus())){
		    Praise praise = new Praise();
		    praise.setPraiseFrom(this.getUserName());
		    praise.setPraiseTo(tasks.getAcceptBy());
		    praise.setCreateBy(this.getUserName());
		    praise.setLastUpdateBy(this.getUserName());
		    praise.setContent("完成任务：" + task.getDemandContent());
		    praiseService.save(praise);
	    }

	    return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        tasksService.deleteById(id);
	    tasksDetailService.deleteByMainId(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Tasks tasks) {
        tasksService.update(tasks);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Tasks tasks = tasksService.findById(id);
        return ResultGenerator.genSuccessResult(tasks);
    }

    @GetMapping
    public Result list(PraiseListQueryDTO param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<Tasks> list = tasksService.findAll(param);
        PageInfo<Tasks> pageInfo = new PageInfo<Tasks>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

	@ApiOperation(value="查询前3个未接受的任务")
	@GetMapping("/top3")
	public Result getNotCloseTop3() {
		List<Tasks> tasks = tasksService.getNotCloseTop3();
		return ResultGenerator.genSuccessResult(tasks);
	}
}
