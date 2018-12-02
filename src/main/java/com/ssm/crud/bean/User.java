package com.ssm.crud.bean;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer userid;
    
    @Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)",message="用户名必须是6-16位（英文或者数字）")
    private String username;

    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$",message="密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字")
    private String pwd;

    private Integer level;

    private String createtime;

    private String modifytime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime == null ? null : modifytime.trim();
    }

	public User() {
		super();
	}

	public User(Integer userid, String username, String pwd, Integer level, String createtime, String modifytime) {
		super();
		this.userid = userid;
		this.username = username;
		this.pwd = pwd;
		this.level = level;
		this.createtime = createtime;
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", pwd=" + pwd + ", level=" + level
				+ ", createtime=" + createtime + ", modifytime=" + modifytime + "]";
	}
    
}