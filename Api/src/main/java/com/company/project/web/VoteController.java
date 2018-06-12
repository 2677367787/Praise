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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

	/**
	 * 选项类型 0 项目组成员,1 自定义选项
	 */
	private final static String OPTIONTYPE = "0";
	@PostMapping
	public Result add(@RequestBody VoteSaveDTO vote) {
        // 保存投票主表信息
		String userName = this.getUserName();
		vote.setCreateBy(userName);
		vote.setLastUpdateBy(this.getUserName());
		voteService.add(vote);
		// 保存选项表信息
		List<VoteOption> optionList;
		if(OPTIONTYPE.equals(vote.getOptionType())){

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
		voteOptionService.save(optionList);

		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		voteService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody Vote vote) {
		voteService.update(vote);
		return ResultGenerator.genSuccessResult();
	}

	@GetMapping("/{id}")
	public Result detail(@PathVariable Integer id) {
		VoteDTO voteList = voteService.findCombinationById(id);
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
