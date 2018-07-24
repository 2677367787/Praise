package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.QueryParam;
import com.company.project.model.Log;
import com.company.project.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2018/06/21.
*/
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    @PostMapping
    public Result add(@RequestBody Log log) {
        logService.save(log);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        logService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Log log) {
        logService.update(log);
        return ResultGenerator.genSuccessResult();
    }

	@GetMapping("/getRecordByRelevant/{userName}")
	public Result getRecordByRelevant(@PathVariable String userName) {

		QueryParam queryParam = new QueryParam();
		queryParam.setUserName(userName);
		List<Log> list = logService.findRecordTopTen(queryParam);

		return ResultGenerator.genSuccessResult(list);
	}

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Log log = logService.findById(id);
        return ResultGenerator.genSuccessResult(log);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Log> list = logService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
