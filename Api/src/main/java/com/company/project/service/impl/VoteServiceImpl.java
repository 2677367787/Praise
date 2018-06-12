package com.company.project.service.impl;

import com.company.project.dao.VoteMapper;
import com.company.project.dto.VoteDTO;
import com.company.project.dto.VoteSaveDTO;
import com.company.project.model.Vote;
import com.company.project.service.VoteService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by tang zhi on 2018/06/09.
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
}
