package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EnvTableHead;
import com.company.project.service.EnvTableHeadService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2019/12/18.
*/
@RestController
@RequestMapping("/env/table/head")
public class EnvTableHeadController {
    @Resource
    private EnvTableHeadService envTableHeadService;

    @PostMapping
    public Result add(@RequestBody EnvTableHead envTableHead) {
        envTableHeadService.save(envTableHead);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        envTableHeadService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody EnvTableHead envTableHead) {
        envTableHeadService.update(envTableHead);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        EnvTableHead envTableHead = envTableHeadService.findById(id);
        return ResultGenerator.genSuccessResult(envTableHead);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EnvTableHead> list = envTableHeadService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
