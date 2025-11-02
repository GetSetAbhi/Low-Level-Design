package com.demo.filelld;

import com.demo.filelld.file.FileSystemNode;

public class FileAndDirectoryMain {
	public static void main(String[] args) {

		FileSystem fs = new FileSystem();

		String path = "/document/cwa_lld/design_file_system";
		String path2 = "/document/cwa_lld/hola_senor";
		String path3 = "/document/cwa_lld/hola_senor/bhad.txt";
		String path4 = "/document/cwa_lld/hola_senor/mod.txt";

		fs.createPath(path);
		fs.createPath(path2);
		fs.createPath(path3);
		fs.createPath(path4);
		
		fs.deleteFileOrDirectory("/document/cwa_lld/hola_senor/mod.txt");

		fs.moveFile("/document/cwa_lld/hola_senor/bhad.txt", "/document/cwa_lld/design_file_system");
		
		
		System.out.println("Ab hoga next");
		
		FileSystemNode node = fs.getNode("/document/cwa_lld/design_file_system/bhad.txt");
		if (node != null) {
			fs.pwd(node);
		}
		
		System.out.println("######5####");
		String path6 = "/document/the_boy/v.txt";
		fs.createPath(path6);
		fs.moveFile("/document/the_boy/v.txt", "/document/cwa_lld/design_file_system");
		fs.displayRootToLeafPath();
		
		String regexAbsolutePath = "/document/cwa_lld/hola_senor/mod.txt";
		fs.cd(regexAbsolutePath);
		
		String regexAbsolutePath2 = "/document/cwa_lld";
		fs.cd(regexAbsolutePath2);
		String regexAbsolutePath3 = "hola_senor";
		fs.cd(regexAbsolutePath3);
		
		String regexAbsolutePath4 = "/document/*/*/*.txt";
		fs.cd(regexAbsolutePath4);
	}
}
