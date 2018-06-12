package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.VoteOptionDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tanggzhi
 */
public interface VoteOptionDetailMapper extends Mapper<VoteOptionDetail> {
	/**
	 * 根据投票项目ID查找所选项
	 * @param voteId 项目ID
	 * @param userName 用户名
	 * @return List<VoteOptionDetail> 结果集
	 */
	List<VoteOptionDetail> getChoosedOption(@Param("voteId")Integer voteId, @Param("userName")String userName);
}