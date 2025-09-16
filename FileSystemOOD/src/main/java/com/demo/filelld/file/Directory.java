package com.demo.filelld.file;

public class Directory extends FileSystemNode {

	public Directory(String name) {
		super(name);
	}

	@Override
	public boolean isFile() {
		return false;
	}

}
