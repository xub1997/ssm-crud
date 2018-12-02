package com.ssm.crud.bean;

import java.io.Serializable;

public class TimeLine implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String title;

    private String color;

    private String content;

    private String time;

    private String createtime;

    private String modifytime;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
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

	public TimeLine() {
		super();
	}

	public TimeLine(Integer id, String title, String color, String content, String time, String createtime,
			String modifytime) {
		super();
		this.id = id;
		this.title = title;
		this.color = color;
		this.content = content;
		this.time = time;
		this.createtime = createtime;
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "TimeLine [id=" + id + ", title=" + title + ", color=" + color + ", content=" + content + ", time="
				+ time + ", createtime=" + createtime + ", modifytime=" + modifytime + "]";
	}
    
}