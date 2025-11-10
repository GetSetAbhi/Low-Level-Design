package com.demo.filelld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.demo.filelld.file.Directory;
import com.demo.filelld.file.File;
import com.demo.filelld.file.FileSystemNode;

public class FileSystem {

	private FileSystemNode root;
	private FileSystemNode currentDirectory;

	public FileSystem() {
		this.root = new Directory("/");
		this.currentDirectory = null;
	}

	public void createPath(String path) {
		System.out.println("Creating path :" + path);
		String[] directories = path.substring(1).split("/");
		FileSystemNode node = this.root;
		for (String val : directories) {
			FileSystemNode nextNode = node.getChildren().computeIfAbsent(val, k -> generate(val));
			if (nextNode != null) {
				if (node != null) {
					nextNode.setParent(node);
				}
			}
			node = nextNode;
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
				child1.setParent(parent2);
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
	
	public String pwd(FileSystemNode node) {
		StringBuilder sb = new StringBuilder();
		while (node != null && node != this.root) {
			sb.insert(0, "/" + node.getName());
			node = node.parent;
		}
		return sb.toString();
	}
	
	public FileSystemNode getNode(String path) {
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
	
	public void cd(String pathPattern) {
		String[] dirs = pathPattern.substring(1).split("/");
		List<FileSystemNode> results = matchesPathPattern(this.root, 0, dirs);
		
		if (results.size() > 0) {
			FileSystemNode node = results.get(0);
			
			if (node.isFile()) {
				System.out.println("Path points to a file, not a directory: " + node.getName());
				return;
			}
			
			this.currentDirectory = node;
			System.out.println("Changed to directory : " + this.pwd(this.currentDirectory));
		}
	}

	private List<FileSystemNode> matchesPathPattern(FileSystemNode node, int index, String[] dirs) {
		List<FileSystemNode> results = new ArrayList<>();
		
		if (index >= dirs.length) {
			results.add(node);
			return results;
		}
		
		String currentValue = dirs[index];
		boolean isRegex = currentValue.equals("*");
		Pattern regex = Pattern.compile(currentValue.replace("*", "[^/]+"));
		
		for (Map.Entry<String, FileSystemNode> childEntry : node.getChildren().entrySet()) {
			FileSystemNode childNode = childEntry.getValue();
			if (isRegex || regex.matcher(childNode.getName()).matches()) {
				results.addAll(matchesPathPattern(childNode, index + 1, dirs));
			}
		}
		return results;
	}

//	public void cd(String pathPattern) {
//		FileSystemNode startNode = pathPattern.startsWith("/") ? root : this.currentDirectory;
//		
//		String[] arr = pathPattern.substring(pathPattern.startsWith("/") ? 1 : 0).split("/");
//		
//		List<FileSystemNode> matches = matchPathPattern(startNode, arr, 0);
//
//        if (matches.isEmpty()) {
//            System.out.println("No match found for: " + pathPattern);
//            return;
//        }
//
//        FileSystemNode target = matches.get(0);
//        if (target.isFile()) {
//            System.out.println("Path points to a file, not a directory: " + target.getName());
//            return;
//        }
//
//        this.currentDirectory = target;
//        System.out.println("Changed directory to: " + pwd(target));
//		
//	}
//
//	private List<FileSystemNode> matchPathPattern(FileSystemNode node, String[] parts, int index) {
//        List<FileSystemNode> result = new ArrayList<>();
//
//        if (index >= parts.length) {
//            result.add(node);
//            return result;
//        }
//
//        String pattern = parts[index];
//        boolean isWildcard = pattern.equals("*");
//        Pattern regex = Pattern.compile(pattern.replace("*", "[^/]+"));
//
//        for (FileSystemNode child : node.getChildren().values()) {
//            if (isWildcard || regex.matcher(child.getName()).matches()) {
//                result.addAll(matchPathPattern(child, parts, index + 1));
//            }
//        }
//
//        return result;
//    }
}
