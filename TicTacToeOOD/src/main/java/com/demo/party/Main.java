package com.demo.party;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter dimensions : ");
		int dimensions = scanner.nextInt();
		
		GameController controller = new GameController(scanner, dimensions);
		
		controller.startGame();
		
		scanner.close();
	}

}
