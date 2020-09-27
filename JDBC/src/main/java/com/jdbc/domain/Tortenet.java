package com.jdbc.domain;

import java.util.Date;

public class Tortenet {

	private Long id;
	private String title;
	private String content;
	private Date posted;
	private Long blogger_id;
	
	public Tortenet() {}

	public Tortenet(String title, String content, Date posted, Long blogger_id) {
		this.title = title;
		this.content = content;
		this.posted = posted;
		this.blogger_id = blogger_id;
	}

	public Tortenet(Long id, String title, String content, Date posted, Long blogger_id) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.posted = posted;
		this.blogger_id = blogger_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public Long getBlogger_id() {
		return blogger_id;
	}

	public void setBlogger_id(Long blogger_id) {
		this.blogger_id = blogger_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
