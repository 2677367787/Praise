package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.PraiseCountDTO;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.Meeting;
import com.company.project.service.MeetingService;
import com.company.project.service.PraiseService;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tang zhi on 2018/05/13.
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController extends BaseController {
	@Resource
	private MeetingService meetingService;

	@Resource
	private PraiseService praiseService;

	@PostMapping
	public Result add(@RequestBody Meeting meeting) {
		meetingService.save(meeting);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		meetingService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody Meeting meeting) {
		meetingService.update(meeting);
		return ResultGenerator.genSuccessResult();
	}

	@GetMapping("/{id}")
	public Result detail(@PathVariable Integer id) {
		Meeting meeting = meetingService.findById(id);
		return ResultGenerator.genSuccessResult(meeting);
	}

	@GetMapping("/newest")
	public Result newest() {
		Meeting meeting = meetingService.getNewest();
		return ResultGenerator.genSuccessResult(meeting);
	}

	@GetMapping("/charts")
	public Result charts() {
		List<PraiseCountDTO> rsultList = new ArrayList<>(5);
		// 查询最近6次双周会议时间
		List<Meeting> list = meetingService.getChart();
		list.forEach(meeting -> {
			// 查询每次会议时间区间段产生的赞
			PraiseListQueryDTO queryParam = new PraiseListQueryDTO();
			
			queryParam.setPraiseDateBegin(DateFormatUtils.format(meeting.getStart(), "yyyy-MM-dd"));
			queryParam.setPraiseDateEnd(DateFormatUtils.format(meeting.getEnd(), "yyyy-MM-dd"));
			PraiseCountDTO praiseCount = new PraiseCountDTO();
			praiseCount = praiseService.getPraiseCountInfo(queryParam);
			if (praiseCount.getPraiseCount() == null) {
				praiseCount.setPraiseCount("0");;
			}
			praiseCount.setIssue("第" + meeting.getTally() + "次");
			rsultList.add(praiseCount);
		});
		return ResultGenerator.genSuccessResult(rsultList);
	}
}
