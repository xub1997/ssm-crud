package com.ssm.crud.bean;

import java.io.Serializable;

public class Comment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private Integer aId;

    private String name;

    private String comment;

    private String commenttime;

    private String replyperson;

    private String reply;

    private String replytime;
    
    private Article article;

    public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime == null ? null : commenttime.trim();
    }

    public String getReplyperson() {
        return replyperson;
    }

    public void setReplyperson(String replyperson) {
        this.replyperson = replyperson == null ? null : replyperson.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public String getReplytime() {
        return replytime;
    }

    public void setReplytime(String replytime) {
        this.replytime = replytime == null ? null : replytime.trim();
    }

	public Comment() {
		super();
	}

	public Comment(Integer id, Integer aId, String name, String comment, String commenttime, String replyperson,
			String reply, String replytime, Article article) {
		super();
		this.id = id;
		this.aId = aId;
		this.name = name;
		this.comment = comment;
		this.commenttime = commenttime;
		this.replyperson = replyperson;
		this.reply = reply;
		this.replytime = replytime;
		this.article = article;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", aId=" + aId + ", name=" + name + ", comment=" + comment + ", commenttime="
				+ commenttime + ", replyperson=" + replyperson + ", reply=" + reply + ", replytime=" + replytime
				+ ", article=" + article + "]";
	}
    
    
}