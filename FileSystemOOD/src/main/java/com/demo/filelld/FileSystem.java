package com.demo.filelld;

import java.util.ArrayList;
import java.util.List;

import com.demo.filelld.file.Directory;
import com.demo.filelld.file.File;
import com.demo.filelld.file.FileSystemNode;

public class FileSystem {

	private FileSystemNode root;

	public FileSystem() {
		this.root = new Directory("/");
	}

	public void createPath(String path) {
		System.out.println("Creating path :" + path);
		String[] directories = path.substring(1).split("/");
		FileSystemNode node = this.root;
		for (String val : directories) {
			node = node.getChildren().computeIfAbsent(val, k -> generate(val));
		}
	}

	public void moveFile(String path1, String path2) {
		FileSystemNode parent1 = getParent(path1);
		FileSystemNode parent2 = getNode(path2);
		
		FileSystemNode child1 = getNode(path1);
		
		if (parent1 != null && child1 != null && parent1.getChildren().containsKey(child1.getName())) {
			parent1.getChildren().remove(child1.getName());
			if (parent2 != null) {
				parent2.getChildren().put(child1.getName(), child1);
			}
		}
	}

	public void deleteFileOrDirectory(String path) {
		System.out.println("Deleting... :" + path);
		FileSystemNode node = getParent(path);
		
		int lastSeparatorIndex = path.lastIndexOf("/");
		String fileOrDirectoryName = path.substring(lastSeparatorIndex + 1);

		if (node.getChildren().containsKey(fileOrDirectoryName)) {
			node.getChildren().remove(fileOrDirectoryName);
		}
		System.out.println("###");
	}

	public void displayRootToLeafPath() {
		System.out.println("\nDisplaying all root to leaf paths\n");
		String sb = "";
		FileSystemNode node = this.root;
		List<String> paths = new ArrayList<>();

		dfs(node, sb + node.getName(), paths);

		for (String path : paths) {
			System.out.println(path);
		}
		System.out.println("###");
	}

	private void dfs(FileSystemNode node, String sb, List<String> paths) {
		if (node != null) {
			if (node.getChildren().isEmpty()) {
				if (sb.length() > 1) {
					int i = sb.lastIndexOf("/");
					sb = sb.substring(0, i);
				}
				paths.add(sb);
				return;
			}
			for (FileSystemNode child : node.getChildren().values()) {
				dfs(child, sb + child.getName() + "/", paths);
			}
		}
	}

	private FileSystemNode getNode(String path) {
		FileSystemNode node = this.root;
		String[] directories = path.substring(1).split("/");
		int n = directories.length;

		for (int i = 0; i < n; i++) {
			if (node.getChildren().containsKey(directories[i])) {
				node = node.getChildren().get(directories[i]);
			} else {
				return null;
			}
		}
		return node;
	}

	private FileSystemNode generate(String val) {
		if (val.contains(".")) {
			return new File(val);
		}
		return new Directory(val);
	}
	
	private FileSystemNode getParent(String path) {
		int lastSeparatorIndex = path.lastIndexOf("/");
		return getNode(path.substring(0, lastSeparatorIndex));
	}
}
