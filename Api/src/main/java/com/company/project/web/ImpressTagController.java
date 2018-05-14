package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.ImpressTag;
import com.company.project.model.Users;
import com.company.project.service.ImpressTagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by tang zhi on 2018/05/13.
*/
@RestController
@RequestMapping("/impress/tag")
public class ImpressTagController extends BaseController{
    @Resource
    private ImpressTagService impressTagService;

    @PostMapping
    public Result add(@RequestBody ImpressTag impressTag) {
    	if(impressTag.getUserName().equals(this.getUserName())) {
    		return ResultGenerator.genFailResult("不能自己给自己加标签!");
    	}
    	impressTag.setCreateBy(this.getUserName());
    	impressTag.setLastUpdateBy(this.getUserName());
        impressTagService.save(impressTag);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        impressTagService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody ImpressTag impressTag) {
        impressTagService.update(impressTag);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        ImpressTag impressTag = impressTagService.findById(id);
        return ResultGenerator.genSuccessResult(impressTag);
    }
    
    @GetMapping("/query/{userName}")
    public Result detail(@PathVariable String userName) {
    	
    	Condition condition = new Condition(ImpressTag.class);
		condition.createCriteria().andEqualTo("userName", userName);
		List<ImpressTag> list = impressTagService.findByCondition(condition);
		
        return ResultGenerator.genSuccessResult(list);
    }
    
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ImpressTag> list = impressTagService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
