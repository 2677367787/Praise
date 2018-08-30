package com.company.project.core;

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
	 * 定时热词分析
	 * 周一至周五8.30提醒点赞
	 * todo 多线程实现
	 */
	//@Scheduled(cron = "0 0/1 * * * ?")
	@Scheduled(cron = "0 30 08 * * 1-5")
	public void operationWords() {
		logger.info("自动发邮件提醒点赞");

		List<Users> list = usersService.getAllUser(null);
		list.forEach(user->{
			//todo 查询出历史给你点赞TOP3,提醒你回谢

		});
	}
}
