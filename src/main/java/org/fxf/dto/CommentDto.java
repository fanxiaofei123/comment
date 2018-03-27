package org.fxf.dto;

import org.fxf.bean.Comment;

public class CommentDto extends Comment{

	/**
     * 隐藏中间4位的手机号
     */
    private String username;
    
    private String title;

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
}
