package com.demo.filelld.file;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class FileSystemNode {
	
	private String name;
	private Map<String, FileSystemNode> children;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	
	public FileSystemNode(String name) {
		this.name = name;
		this.children = new HashMap<>();
	}
	
	public abstract boolean isFile();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, FileSystemNode> getChildren() {
		return children;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
}
