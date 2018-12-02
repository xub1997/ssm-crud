package com.ssm.crud.bean;

import java.io.Serializable;

public class Article implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String title;

    private String author;

    private Integer cId;

    private String createtime;

    private String modifytime;

    private String content;
    
    private Category category;

    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Article() {
		super();
	}

	public Article(Integer id, String title, String author, Integer cId, String createtime, String modifytime,
			String content) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.cId = cId;
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", author=" + author + ", cId=" + cId + ", createtime="
				+ createtime + ", modifytime=" + modifytime + ", content=" + content + "]";
	}

	
    
}