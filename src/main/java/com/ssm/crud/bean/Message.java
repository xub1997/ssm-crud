package com.ssm.crud.bean;

import java.io.Serializable;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String name;

    private String email;

    private String msg;

    private String remark;

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

	public Message() {
		super();
	}

	public Message(Integer id, String name, String email, String msg, String remark, String createtime) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.msg = msg;
		this.remark = remark;
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", name=" + name + ", email=" + email + ", msg=" + msg + ", remark=" + remark
				+ ", createtime=" + createtime + "]";
	}
    
}