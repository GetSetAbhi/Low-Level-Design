package com.demo.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
	private Scanner scanner;
	private Board board;
	private List<Player> players;
	
	public GameController(Scanner scan, int dimensions) {
		this.scanner = scan;
		this.board = new Board(dimensions);
		this.players = new ArrayList<>();
	}
	
	public void startGame() {
		this.addPlayer();
		this.addPlayer();
		int moveCount = 0;
		int totalPlayers = this.players.size();
		int totalMoves = board.getDimensions()*board.getDimensions();
		
		while (moveCount < totalMoves) {
			int index = moveCount % totalPlayers;
			Player player = this.players.get(index);
			boolean isMoveValid = false;
			board.printGrid();
			int x = 0;
			int y = 0;
			while (!isMoveValid) {
				System.out.println(player.getName() + " please make your move, enter coordinates");
				System.out.println("Enter x : ");
				x = this.scanner.nextInt();
				System.out.println("Enter y : ");
				y = this.scanner.nextInt();
				isMoveValid = board.isMoveValid(player, x, y);
				if (isMoveValid) {
					board.markMove(player, x, y);
				}
			}
			if (player.isWinner(x, y)) {
				board.printGrid();
				System.out.println(player.getName() + " has won");
				break;
			}
			moveCount += 1;
		}
		
		if (moveCount == totalMoves) {
			board.printGrid();
			System.out.println("This game is a draw");
		}
	}
	
	private void addPlayer() {
		System.out.println("Add player name : ");
		String name = this.scanner.next();
		System.out.println("Add player symbol : ");
		char symbol = this.scanner.next().charAt(0);
		this.players.add(new Player(name, symbol, board.getDimensions()));
	}
}
