package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EnvTable;
import com.company.project.service.EnvTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2019/12/01.
*/
@RestController
@RequestMapping("/env/table")
public class EnvTableController {
    @Resource
    private EnvTableService envTableService;

    @PostMapping
    public Result add(@RequestBody EnvTable envTable) {
        envTableService.save(envTable);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        envTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody EnvTable envTable) {
        envTableService.update(envTable);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        EnvTable envTable = envTableService.findById(id);
        return ResultGenerator.genSuccessResult(envTable);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EnvTable> list = envTableService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
