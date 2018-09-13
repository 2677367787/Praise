package com.company.project.web;

import com.company.project.core.BaseController;
import com.company.project.core.EmaliUtils;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dto.PieChartDTO;
import com.company.project.dto.PraiseListDTO;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.dto.QueryParam;
import com.company.project.model.*;
import com.company.project.service.*;
import com.company.project.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tang zhi
 * @date 2018/04/18
 */
@RestController
@RequestMapping("/praise")
public class PraiseController extends BaseController{
	@Resource
	private PraiseService praiseService;

	@Resource
	private UsersService usersService;

	@Resource
	private MeetingService meetingService;

	@Resource
	private EmailService emailService;

	@Resource
	private LogService logService;

	@PostMapping
	public Result add(@RequestBody Praise praise) {
		String userName = this.getUserName();
		praise.setPraiseFrom(userName);
		praise.setCreateBy(userName);
		praise.setLastUpdateBy(userName);
		praiseService.Add(praise);
		// 点赞记录
		Log log = new Log();
		log.setRelevant(praise.getPraiseTo());
		log.setType(1);
		log.setBeFrom(userName);
		log.setCreateBy(userName);
		log.setLastUpdateBy(userName);
		logService.save(log);
		// 发送邮件
		QueryParam queryParam = getParam();
		List<Praise> top3List = praiseService.getPraiseTop3(queryParam);
		List<Users> usersList = usersService.getAllUser(new Users());
		String from = this.getNickName() + "(" + this.getUserName() + ")";
		String subject = from + "赞了" + praise.getUniqueName() + ":" + praise.getContent();
		//EmaliUtils.praiseAdd(to,,top3List,usersList);
		Email email = new Email();
		email.setSender("srm");
		email.setRecipients("6396000749");
		email.setCc("6396000749");
		email.setSubject(subject);
		email.setMbText("内容");
		email.setClickSee("http://10.5.4.24:9001");
		email.setSystemName("点赞系统");
		email.setRelationId(praise.getPraiseId());
		email.setCreateBy(this.getUserName());
		email.setLastUpdateBy(this.getUserName());
		email.setLinkAddress("http://10.5.4.24:9001");
		emailService.save(email);
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

	@GetMapping("/to/list")
	public Result getPraiseTo(QueryParam param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		param.setUserName(this.getUserName());
		List<Praise> list = praiseService.getPraiseToList(param);
		PageInfo<Praise> pageInfo = new PageInfo<>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@GetMapping("/detail")
	public Result getPraiseDetail(PraiseListQueryDTO praise) {
		List<PraiseListDTO> list = praiseService.getPraiseDetail(praise);
		return ResultGenerator.genSuccessResult(list);
	}

	/**
	 * 点赞画像
	 * @param userName 用户名
	 * @return Result 结果
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
	 * @param userName 用户名
	 * @return Result 结果
	 */
	@GetMapping("/from/portrait/{userName}")
	public Result getFromPortrait(@PathVariable String userName) {
		PraiseListQueryDTO praise = new PraiseListQueryDTO();
		praise.setUserName(userName);
		List<PieChartDTO> list = praiseService.getPraisePieChartData(praise);
		return ResultGenerator.genSuccessResult(list);
	}

	/**
	 * 获赞top3人员
	 * @return
	 */
	@GetMapping("/top")
	public Result getPraiseDetail() {
		QueryParam queryParam = getParam();
		List<Praise> list = praiseService.getPraiseTop3(queryParam);
		return ResultGenerator.genSuccessResult(list);
	}

	@GetMapping("/probably")
	public Result getProbablyPraise() {

		QueryParam queryParam = getParam();
		queryParam.setUserName(this.getUserName());
		List<HashMap<String,String>> list = praiseService.getProbablyPraise(queryParam);
		return ResultGenerator.genSuccessResult(list);
	}

	private QueryParam getParam(){
		Meeting meeting = meetingService.getNewest();
		QueryParam queryParam = new QueryParam();
		// 获取最新会议的结束日期
		Date end = meeting.getEnd();
		// 获取当前日期对比
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(new Date());

		try {
			Date now = sdf.parse(s);
			// 如果今天已经超过最新的会议周期 说明还没有发布新会议，默认会议范围为上次结束日期到今天
			if(end.before(now)){
				DateUtils.addDay(end,1);
				queryParam.setBeginDate(DateUtils.getDateFormat(end));
				queryParam.setEndDate(DateUtils.getDateFormat(now));
			}else{
				queryParam.setBeginDate(DateUtils.getDateFormat(meeting.getStart()));
				queryParam.setEndDate(DateUtils.getDateFormat(end));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return queryParam;
	}
}
