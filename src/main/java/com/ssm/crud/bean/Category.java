package com.ssm.crud.bean;

import java.io.Serializable;

public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String categoryname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

	public Category() {
		super();
	}

	public Category(Integer id, String categoryname) {
		super();
		this.id = id;
		this.categoryname = categoryname;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryname=" + categoryname + "]";
	}
    
}