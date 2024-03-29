package com.company.project.core;

import com.company.project.dto.PraiseListQueryDTO;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

/**
 * 定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加。
 */
public interface Mapper<T>
        extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T> {

	/**
	 * 查询所有数据
	 * @param param 参数
	 * @return 返回 List<T>
	 */
	List<T> findAll(PraiseListQueryDTO param);
}
