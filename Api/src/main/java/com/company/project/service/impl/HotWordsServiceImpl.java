package com.company.project.service.impl;

import com.company.project.dao.HotWordsMapper;
import com.company.project.model.HotWords;
import com.company.project.service.HotWordsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by tang zhi on 2018/07/08.
 */
@Service
@Transactional
public class HotWordsServiceImpl extends AbstractService<HotWords> implements HotWordsService {
    @Resource
    private HotWordsMapper hotWordsMapper;

	/**
	 * 根据用户名删除所有相关数据
	 * @param userName 用户名
	 */
	@Override
	public void deleteByUserName(String userName) {
		hotWordsMapper.deleteByUserName(userName);
	}

	/**
	 * 根据用户名查找所有热词
	 * @param userName 用户名
	 */
	@Override
	public List<HotWords> getListByUserName(String userName) {
		return hotWordsMapper.getListByUserName(userName);
	}
}
