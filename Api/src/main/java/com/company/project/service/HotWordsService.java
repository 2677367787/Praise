package com.company.project.service;
import com.company.project.model.HotWords;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by tang zhi on 2018/07/08.
 */
public interface HotWordsService extends Service<HotWords> {

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
