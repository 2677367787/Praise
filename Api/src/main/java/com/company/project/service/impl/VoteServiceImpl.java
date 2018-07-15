package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.VoteMapper;
import com.company.project.dto.VoteDTO;
import com.company.project.dto.VoteSaveDTO;
import com.company.project.model.Vote;
import com.company.project.service.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * @author tang zhi
 * @date 2018/06/09
 */
@Service
@Transactional
public class VoteServiceImpl extends AbstractService<Vote> implements VoteService {
    @Resource
    private VoteMapper voteMapper;

	@Override
	public VoteDTO findCombinationById(Integer voteId) {
		return voteMapper.findCombinationById(voteId);
	}

	/**
	 * 保存一条投票记录
	 * @param vote 实体对象
	 * @return 返回主键ID
	 */
	@Override
	public void add(VoteSaveDTO vote) {
		voteMapper.add(vote);
	}

	/**
	 * 根据主键ID关联删除缩有记录
	 * @param id 主表ID
	 */
	@Override
	public void unionDeleteById(Integer id) {
		mapper.deleteByPrimaryKey(id);
		voteMapper.deleteOption(id);
		voteMapper.deleteOptionDetail(id);
	}

	/**
	 * 根据主键ID关联删除缩有记录
	 * @param id 主表ID
	 */
	@Override
	public void deleteOption(Integer id) {
		voteMapper.deleteOption(id);
	}

	/**
	 * 根据主键ID关联删除所有投票记录
	 * @param id 主表ID
	 */
	@Override
	public void deleteOptionDetail(Integer id) {
		voteMapper.deleteOptionDetail(id);
	}

	/**
	 * 查找还未关闭前3个投票
	 * @return List 集合
	 */
	@Override
	public List<Vote> getNotCloseTop3() {
		return voteMapper.getNotCloseTop3();
	}
}
