package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.dto.VoteDTO;
import com.company.project.model.Vote;

import java.util.List;

/**
 * @author tanggzhi
 */
public interface VoteMapper extends Mapper<Vote> {

	/**
	 * 通过ID查找组合实体对象
	 * @param voteId 投票内容ID
	 * @return 返回对象集合
	 */
	VoteDTO findCombinationById(Integer voteId);

	/**
	 * 保存一条投票记录
	 * @param model 实体对象
	 */
	void add(Vote model);

	void deleteOption(Integer id);

	void deleteOptionDetail(Integer id);

	/**
	 * 查找还未关闭前3个投票
	 * @return List 集合
	 */
	List<Vote> getNotCloseTop3();
}