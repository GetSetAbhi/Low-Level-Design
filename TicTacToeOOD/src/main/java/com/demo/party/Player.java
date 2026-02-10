package com.demo.party;

public class Player {

	private char symbol;
	private String name;
	private ScoreManager manager;
	
	public Player(String name, char symbol, int dimensions) {
		this.name = name;
		this.symbol = symbol;
		this.manager = new ScoreManager(dimensions);
		System.out.println(name + " with symbol "+ symbol +" added to the game");
	}
	
	public void markMove(int x, int y) {
		this.manager.markMove(x, y);
	}
	
	public boolean isWinner(int x, int y) {
		return this.manager.isWinner(x, y);
	}

	public char getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public ScoreManager getManager() {
		return manager;
	}
}
