package com.demo.filelld;

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

		fs.displayRootToLeafPath();
		
		fs.deleteFileOrDirectory("/document/cwa_lld/hola_senor/mod.txt");
		
		fs.displayRootToLeafPath();

		fs.moveFile("/document/cwa_lld/hola_senor/bhad.txt", "/document/cwa_lld/design_file_system");
		
		fs.displayRootToLeafPath();
	}
}
