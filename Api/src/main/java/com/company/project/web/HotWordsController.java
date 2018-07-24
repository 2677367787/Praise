package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.schedule.HotWordsApp;
import com.company.project.model.HotWords;
import com.company.project.service.HotWordsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2018/07/08.
*/
@RestController
@RequestMapping("/hot/words")
public class HotWordsController {
    @Resource
    private HotWordsService hotWordsService;

    @PostMapping
    public Result add(@RequestBody HotWords hotWords) {
        hotWordsService.save(hotWords);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        hotWordsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody HotWords hotWords) {
        hotWordsService.update(hotWords);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{userName}")
    public Result detail(@PathVariable String userName) {
        List<HotWords> hotWords = hotWordsService.getListByUserName(userName);
        return ResultGenerator.genSuccessResult(hotWords);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<HotWords> list = hotWordsService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
