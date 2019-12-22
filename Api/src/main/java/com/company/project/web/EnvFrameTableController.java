package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EnvFrameTable;
import com.company.project.service.EnvFrameTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2019/12/18.
*/
@RestController
@RequestMapping("/env/frame/table")
public class EnvFrameTableController {
    @Resource
    private EnvFrameTableService envFrameTableService;

    @PostMapping
    public Result add(@RequestBody EnvFrameTable envFrameTable) {
        envFrameTableService.save(envFrameTable);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        envFrameTableService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody EnvFrameTable envFrameTable) {
        envFrameTableService.update(envFrameTable);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        EnvFrameTable envFrameTable = envFrameTableService.findById(id);
        return ResultGenerator.genSuccessResult(envFrameTable);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EnvFrameTable> list = envFrameTableService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
