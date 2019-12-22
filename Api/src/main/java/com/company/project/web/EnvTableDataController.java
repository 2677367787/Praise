package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EnvTableData;
import com.company.project.service.EnvTableDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2019/12/18.
*/
@RestController
@RequestMapping("/env/table/data")
public class EnvTableDataController {
    @Resource
    private EnvTableDataService envTableDataService;

    @PostMapping
    public Result add(@RequestBody EnvTableData envTableData) {
        envTableDataService.save(envTableData);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        envTableDataService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody EnvTableData envTableData) {
        envTableDataService.update(envTableData);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        EnvTableData envTableData = envTableDataService.findById(id);
        return ResultGenerator.genSuccessResult(envTableData);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EnvTableData> list = envTableDataService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
