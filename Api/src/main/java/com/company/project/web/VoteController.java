package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.dto.VoteDTO;
import com.company.project.dto.VoteSaveDTO;
import com.company.project.model.Users;
import com.company.project.model.Vote;
import com.company.project.model.VoteOption;
import com.company.project.service.UsersService;
import com.company.project.service.VoteOptionService;
import com.company.project.service.VoteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tang zhi
 * @date 2018/06/09
 */
@RestController
@RequestMapping("/vote")
public class VoteController extends BaseController {
	@Resource
	private VoteService voteService;

	@Resource
	private VoteOptionService voteOptionService;

	@Resource
	private UsersService usersService;

	public VoteController(HttpServletRequest request) {
		super(request);
	}

	@PostMapping
	public Result add(@RequestBody VoteSaveDTO vote) {
        // 保存投票主表信息
		String userName = this.getUserName();
		vote.setCreateBy(userName);
		vote.setLastUpdateBy(this.getUserName());
		voteService.add(vote);
		// 保存选项表信息
		List<VoteOption> optionList = saveVoteOptions(vote, userName);
		voteOptionService.save(optionList);

		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		voteService.unionDeleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody Vote vote) {
		vote.setParticipants(0);
		voteService.update(vote);
		String userName = this.getUserName();
		// 删除之前的选项
		voteService.deleteOption(vote.getVoteId());
		// 删除所有投票信息
		voteService.deleteOptionDetail(vote.getVoteId());
		// 保存选项表信息
		List<VoteOption> optionList = saveVoteOptions(vote, userName);
		voteOptionService.save(optionList);

		return ResultGenerator.genSuccessResult();
	}

	private List<VoteOption> saveVoteOptions(Vote vote, String userName) {
		List<VoteOption> optionList;
		if(vote.getOptionType() == 0){
			List<Users> usersList = usersService.getAllUser(new Users());
			optionList = new ArrayList<>(5);
			usersList.forEach(item->{
				VoteOption vo = new VoteOption();
				Date date = new Date();
				vo.setOptionText(item.getNickName()+"(" + item.getUserName() + ")");
				vo.setVoteId(vote.getVoteId());
				vo.setCreateBy(userName);
				vo.setLastUpdateBy(userName);
				vo.setCreateDate(date);
				vo.setLastUpdateDate(date);
				optionList.add(vo);
			});

		}else{
			optionList = vote.getVoteOptions();
			optionList.forEach(item -> {
				Date date = new Date();
				item.setVoteId(vote.getVoteId());
				item.setCreateBy(userName);
				item.setLastUpdateBy(userName);
				item.setCreateDate(date);
				item.setLastUpdateDate(date);
			});
		}
		return optionList;
	}

	@GetMapping("/{id}")
	public Result detail(@PathVariable Integer id) {
		VoteDTO voteList = voteService.findCombinationById(id);
		return ResultGenerator.genSuccessResult(voteList);
	}

	@ApiOperation(value="查询前3个未关闭的投票")
	@GetMapping("/top3")
	public Result getNotCloseTop3() {
		List<Vote> voteList = voteService.getNotCloseTop3();
		return ResultGenerator.genSuccessResult(voteList);
	}

	@GetMapping
	public Result list(PraiseListQueryDTO param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<Vote> list = voteService.findAll(param);
		PageInfo<Vote> pageInfo = new PageInfo<>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
