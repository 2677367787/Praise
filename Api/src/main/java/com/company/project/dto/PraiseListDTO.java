package com.company.project.dto;

import java.io.Serializable;
import java.util.Date;

public class PraiseListDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11241241241242123L;

	/**
	 * 内容
	 */
	private String content;

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	/**
	 * 目标
	 */
	private String praiseTo;

	public void setPraiseTo(String praiseTo) {
		this.praiseTo = praiseTo;
	}

	public String getPraiseTo() {
		return praiseTo;
	}

	/**
	 * 来自
	 */
	private String praiseFrom;

	public void setPraiseFrom(String praiseFrom) {
		this.praiseFrom = praiseFrom;
	}

	public String getPraiseFrom() {
		return praiseFrom;
	}

	/**
	 * 主键(自增长)
	 */
	private Integer praiseId;

	public void setPraiseId(Integer praiseId) {
		this.praiseId = praiseId;
	}

	public Integer getPraiseId() {
		return praiseId;
	}

	/**
	 * 性别
	 */
	private Integer sex;

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return sex;
	}

	/**
	 * 个性签名
	 */
	private String signature;

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignature() {
		return signature;
	}

	/**
	 * 头像路径
	 */
	private String portrait;

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getPortrait() {
		return portrait;
	}

	/**
	 * 密码
	 */
	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * 姓名
	 */
	private String nickName;

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	/**
	 * 用户名编号
	 */
	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	/**
	 * 主键(自增长)
	 */
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	/*
	 * 创建时间
	 */
	private Date createDate;

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}
}