package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EnvFrame;
import com.company.project.service.EnvFrameService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2019/12/18.
*/
@RestController
@RequestMapping("/env/frame")
public class EnvFrameController {
    @Resource
    private EnvFrameService envFrameService;

    @PostMapping
    public Result add(@RequestBody EnvFrame envFrame) {
        envFrameService.save(envFrame);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        envFrameService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody EnvFrame envFrame) {
        envFrameService.update(envFrame);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        EnvFrame envFrame = envFrameService.findById(id);
        return ResultGenerator.genSuccessResult(envFrame);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EnvFrame> list = envFrameService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
