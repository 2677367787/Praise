package com.company.project.core;

import com.company.project.dto.PieChartDTO;
import com.company.project.dto.PraiseListDTO;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.HotWords;
import com.company.project.model.Users;
import com.company.project.service.HotWordsService;
import com.company.project.service.PraiseService;
import com.company.project.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自动邮件提醒点赞
 */
@Configuration
@EnableScheduling
public class AutoRemind {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UsersService usersService;

	@Resource
	private PraiseService praiseService;

	/**
	 * 邮件提醒点赞
	 * 周一至周五8.30提醒点赞
	 * todo 多线程实现
	 */
	//@Scheduled(cron = "0 0/1 * * * ?")
	@Scheduled(cron = "0 10 08 * * 1")
	public void operationWords() {
		logger.info("自动发邮件提醒点赞");
		//集体邮件
		String cbtext = "元气满满的周一,给过去一周努力/勤奋/可爱/帅气/乐于助人的小伙伴点个赞吧~~";
	}

	/**
	 * 邮件提醒点赞
	 * 周二至周四提醒回赞
	 * todo 多线程实现
	 */
	//@Scheduled(cron = "0 0/1 * * * ?")
	@Scheduled(cron = "0 10 08 * * 2-4")
	public void atuoRemind() {
		logger.info("自动发邮件提醒点赞");

		List<Users> list = usersService.getAllUser(null);
		list.forEach(user->{
			List<PieChartDTO> pieChartList = praiseService.selectPraiseUserToEmail(user.getUserName());
			String cbtext = pieChartList.stream().map(PieChartDTO::getName).collect(Collectors.joining(","));
			cbtext = "给过去一周努力的小伙伴点个赞吧~~"+cbtext;
		});
	}
}
