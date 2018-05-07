package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Users {
    /**
     * 主键(自增长)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名编号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 姓名
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像路径
     */
    private String portrait;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 注册时间
     */
    @Column(name = "register_date")
    private Date registerDate;

    /**
     * 注册IP
     */
    @Column(name = "register_ip")
    private String registerIp;

    /**
     * 最后一次登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登录IP
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 是否有效
     */
    @Column(name = "enable_flag")
    private Integer enableFlag;

    /**
     * 获取主键(自增长)
     *
     * @return id - 主键(自增长)
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键(自增长)
     *
     * @param id 主键(自增长)
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名编号
     *
     * @return user_name - 用户名编号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名编号
     *
     * @param userName 用户名编号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取姓名
     *
     * @return nick_name - 姓名
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置姓名
     *
     * @param nickName 姓名
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像路径
     *
     * @return portrait - 头像路径
     */
    public String getPortrait() {
        return portrait;
    }

    /**
     * 设置头像路径
     *
     * @param portrait 头像路径
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     * 获取个性签名
     *
     * @return signature - 个性签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 设置个性签名
     *
     * @param signature 个性签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取注册时间
     *
     * @return register_date - 注册时间
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 设置注册时间
     *
     * @param registerDate 注册时间
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 获取注册IP
     *
     * @return register_ip - 注册IP
     */
    public String getRegisterIp() {
        return registerIp;
    }

    /**
     * 设置注册IP
     *
     * @param registerIp 注册IP
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    /**
     * 获取最后一次登录时间
     *
     * @return login_time - 最后一次登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置最后一次登录时间
     *
     * @param loginTime 最后一次登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登录IP
     *
     * @return login_ip - 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     *
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取是否有效
     *
     * @return enable_flag - 是否有效
     */
    public Integer getEnableFlag() {
        return enableFlag;
    }

    /**
     * 设置是否有效
     *
     * @param enableFlag 是否有效
     */
    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }
}