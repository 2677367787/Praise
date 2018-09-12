package com.company.project.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.company.project.model.Praise;

import java.util.List;

/**
 * @author tanggzhi
 */
public class QueryParam {
	/**
	 * 工号
	 */
	private String userName;
	/**
	 * 开始时间
	 */
	@JSONField(format="yyyy-MM-dd")
	private String beginDate;
	/**
	 * 结束时间
	 */
	@JSONField (format="yyyy-MM-dd")
	private String endDate;

	private int pageNum;

	private int pageSize;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
