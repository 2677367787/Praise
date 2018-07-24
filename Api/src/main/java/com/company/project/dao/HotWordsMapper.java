package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.HotWords;

import java.util.List;

public interface HotWordsMapper extends Mapper<HotWords> {
	/**
	 * 根据用户名删除所有相关数据
	 * @param userName 用户名
	 */
	void deleteByUserName(String userName);

	/**
	 * 根据用户名查找所有热词
	 * @param userName 用户名
	 */
	List<HotWords> getListByUserName(String userName);
}