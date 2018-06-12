package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.OptionDetailSave;
import com.company.project.model.Vote;
import com.company.project.model.VoteOptionDetail;
import com.company.project.service.VoteOptionDetailService;
import com.company.project.service.VoteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
*
 * @author tang zhi
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/vote/option/detail")
public class VoteOptionDetailController extends BaseController {
    @Resource
    private VoteOptionDetailService voteOptionDetailService;

    @Resource
    private VoteService voteService;

    @PostMapping
    public Result add(@RequestBody OptionDetailSave optionDetailSave) {
	    List<VoteOptionDetail>  voteOptionDetailList = optionDetailSave.getVoteOptionDetailList();
	    voteOptionDetailList.forEach(vod->{
	    	Date date = new Date();
	    	vod.setVoters(this.getUserName());
	    	vod.setCreateBy(this.getUserName());
	    	vod.setLastUpdateBy(this.getUserName());
	    	vod.setCreateDate(date);
	    	vod.setLastUpdateDate(date);
	    	vod.setEnableFlag(1);
	    });
        voteOptionDetailService.save(voteOptionDetailList);
        Vote vote = voteService.findById(optionDetailSave.getVoteId());
        vote.setParticipants(vote.getParticipants()+1);
	    voteService.update(vote);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        voteOptionDetailService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody VoteOptionDetail voteOptionDetail) {
        voteOptionDetailService.update(voteOptionDetail);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        VoteOptionDetail voteOptionDetail = voteOptionDetailService.findById(id);
        return ResultGenerator.genSuccessResult(voteOptionDetail);
    }

    @GetMapping("/choosed/{voteId}")
    public Result getChoosedOption(@PathVariable Integer voteId) {
	    List<VoteOptionDetail> voteOptionDetails = voteOptionDetailService.getChoosedOption(voteId,this.getUserName());
        return ResultGenerator.genSuccessResult(voteOptionDetails);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<VoteOptionDetail> list = voteOptionDetailService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
