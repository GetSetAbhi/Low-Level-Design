package com.demo.filelld.file;

public class File extends FileSystemNode {
	
	public File(String name) {
		super(name);
	}

	@Override
	public boolean isFile() {
		return true;
	}
}
