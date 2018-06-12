package com.company.project.service.impl;

import com.company.project.dao.VoteOptionDetailMapper;
import com.company.project.model.VoteOptionDetail;
import com.company.project.service.VoteOptionDetailService;
import com.company.project.core.AbstractService;
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
public class VoteOptionDetailServiceImpl extends AbstractService<VoteOptionDetail> implements VoteOptionDetailService {
    @Resource
    private VoteOptionDetailMapper voteOptionDetailMapper;

	/**
	 * 根据投票项目ID查找所选项
	 * @param voteId 项目ID
	 * @param userName 用户名
	 * @return List<VoteOptionDetail> 结果集
	 */
	@Override
	public List<VoteOptionDetail> getChoosedOption(Integer voteId, String userName) {
		return voteOptionDetailMapper.getChoosedOption(voteId,userName);
	}
}
