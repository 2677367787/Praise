package com.company.project.service;

import com.company.project.core.Service;
import com.company.project.dto.VoteDTO;
import com.company.project.dto.VoteSaveDTO;
import com.company.project.model.Vote;

import java.util.List;


/**
 *
 * @author tang zhi
 * @date 2018/06/09
 */
public interface VoteService extends Service<Vote> {
	/**
	 * 通过ID查找组合实体对象
	 * @param voteId 投票内容ID
	 * @return 返回对象集合
	 */
	VoteDTO findCombinationById(Integer voteId);

	/**
	 * 保存一条投票记录
	 * @param vote 实体对象
	 * @return 返回主键ID
	 */
	void add(VoteSaveDTO vote);

	/**
	 * 根据主键ID关联删除缩有记录
	 * @param id 主表ID
	 */
	void unionDeleteById(Integer id);

	/**
	 * 根据主键ID关联删除缩有记录
	 * @param id 主表ID
	 */
	void deleteOption(Integer id);

	/**
	 * 根据主键ID关联删除缩有记录
	 * @param id 主表ID
	 */
	void deleteOptionDetail(Integer id);

	/**
	 * 查找还未关闭前3个投票
	 * @return List 集合
	 */
	List<Vote> getNotCloseTop3();
}
