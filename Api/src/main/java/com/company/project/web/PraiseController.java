package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.dto.PraiseTitleDTO;
import com.company.project.model.Praise;
import com.company.project.model.Users;
import com.company.project.service.PraiseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
* Created by tang zhi on 2018/04/18.
*/
@RestController
@RequestMapping("/praise")
public class PraiseController extends BaseController{
    @Resource
    private PraiseService praiseService;

    @PostMapping
    public Result add(@RequestBody Praise praise) {
    	String userName = this.getUserName();
    	Date date = new Date();
    	praise.setPraiseFrom(userName);
    	praise.setCreateBy(userName);
    	praise.setLastUpdateBy(userName);
    	praise.setCreateDate(date);
    	praise.setLastUpdateDate(date);
        praiseService.save(praise);
        
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        praiseService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Praise praise) {
        praiseService.update(praise);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Praise praise = praiseService.findById(id);
        return ResultGenerator.genSuccessResult(praise);
    }

//    @GetMapping
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Praise> list = praiseService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }
    
    @GetMapping("/title")
    public Result title() {
    	List<PraiseTitleDTO> list = praiseService.getPraiseCountInfo();
        return ResultGenerator.genSuccessResult(list);
    }
    
    @GetMapping("/list")
    public Result list(PraiseListQueryDTO praise) {
    	List<Praise> list = praiseService.getPraiseList(praise);
        return ResultGenerator.genSuccessResult(list);
    }
    
    @GetMapping("/detail")
    public Result getPraiseDetail(PraiseListQueryDTO praise) {
//    	Condition condition = new Condition(Praise.class);
//    	if(StringUtils.isNotBlank(from)) {
//    		condition.createCriteria().andEqualTo("praiseFrom", from); 
//    	}
//    	if(StringUtils.isNotBlank(to)) {
//    		condition.createCriteria().andEqualTo("praiseTo", to); 
//    	}
    	List<Praise> list = praiseService.getPraiseDetail(praise);
    	return ResultGenerator.genSuccessResult(list);
    }
}
