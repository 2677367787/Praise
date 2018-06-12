package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.PieChartDTO;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Praise;
import com.company.project.service.PraiseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    	praise.setPraiseFrom(userName);
    	praise.setCreateBy(userName);
    	praise.setLastUpdateBy(userName);
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

    @GetMapping("/title")
    public Result title() {
        return ResultGenerator.genSuccessResult("");
    }
    
    @GetMapping("/list")
    public Result list(PraiseListQueryDTO praise) {
    	List<Praise> list = praiseService.getPraiseList(praise);
        return ResultGenerator.genSuccessResult(list);
    }
    
    @GetMapping("/detail")
    public Result getPraiseDetail(PraiseListQueryDTO praise) {
    	List<Praise> list = praiseService.getPraiseDetail(praise);
    	return ResultGenerator.genSuccessResult(list);
    }
    
    /**
     * 点赞画像
     * @param userName
     * @return
     */
    @GetMapping("/to/portrait/{userName}")
    public Result getToPortrait(@PathVariable String userName) {
    	PraiseListQueryDTO praise = new PraiseListQueryDTO();
    	praise.setUserName(userName);
    	praise.setPraiseTo("true");
    	List<PieChartDTO> list = praiseService.getPraisePieChartData(praise);
    	return ResultGenerator.genSuccessResult(list);
    }
    
    /**
     * 获赞画像
     * @param userName
     * @return
     */
    @GetMapping("/from/portrait/{userName}")
    public Result getFromPortrait(@PathVariable String userName) {
    	PraiseListQueryDTO praise = new PraiseListQueryDTO();
    	praise.setUserName(userName);
    	List<PieChartDTO> list = praiseService.getPraisePieChartData(praise);
    	return ResultGenerator.genSuccessResult(list);
    }
}
