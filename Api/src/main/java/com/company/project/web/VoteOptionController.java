package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.VoteOption;
import com.company.project.service.VoteOptionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2018/06/09.
*/
@RestController
@RequestMapping("/vote/option")
public class VoteOptionController {
    @Resource
    private VoteOptionService voteOptionService;

    @PostMapping
    public Result add(@RequestBody VoteOption voteOption) {
        voteOptionService.save(voteOption);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        voteOptionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody VoteOption voteOption) {
        voteOptionService.update(voteOption);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        VoteOption voteOption = voteOptionService.findById(id);
        return ResultGenerator.genSuccessResult(voteOption);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<VoteOption> list = voteOptionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
