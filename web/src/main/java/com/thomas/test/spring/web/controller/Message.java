package com.thomas.test.spring.web.controller;

public class Message {

	public String content;

	public Message(String content) {
		super();
		this.content = content;
	}

	public Message(Throwable e) {
		this("");
		StringBuilder sb = new StringBuilder();
		sb.append(e.getMessage());
		sb.append("\n");
		for (StackTraceElement s : e.getStackTrace()) {
			sb.append(s.toString());
			sb.append("\n");
		}
		setContent(sb.toString());
	}

	public String getContent() {
		return content;
	}

	public void setContent(
			String content) {
		this.content = content;
	}

}
