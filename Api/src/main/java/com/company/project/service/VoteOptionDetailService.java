package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.model.VoteOptionDetail;

import java.util.List;


/**
 * Created by tang zhi on 2018/06/09.
 */
public interface VoteOptionDetailService extends Service<VoteOptionDetail> {

	/**
	 * 根据投票项目ID查找所选项
	 * @param voteId 项目ID
	 * @param userName 用户名
	 * @return List<VoteOptionDetail> 结果集
	 */
	List<VoteOptionDetail> getChoosedOption(Integer voteId,String userName);
}
