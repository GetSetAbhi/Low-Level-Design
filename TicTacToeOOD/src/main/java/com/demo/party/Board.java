package com.demo.party;

public class Board {

	private int dimensions;
	private Character[][] grid;
	
	public Board(int dimensions) {
		this.dimensions = dimensions;
		this.grid = new Character[dimensions][dimensions];
		for (int i = 0; i < this.dimensions; i++) {
			for (int j = 0; j < this.dimensions; j++) {
				this.grid[i][j] = '_';
			}
		}
	}
	
	public void markMove(Player player, int x, int y) {
		this.grid[x][y] = player.getSymbol();
		player.markMove(x, y);
	}
	
	public boolean isMoveValid(Player player, int x, int y) {
		if (x < dimensions && x >= 0 && y >= 0 && y < dimensions) {
			if (grid[x][y] == '_') {
				return true;
			}
		}
		return false;
	}

	public int getDimensions() {
		return dimensions;
	}
	
	public void printGrid() {
		for (int i = 0; i < this.dimensions; i++) {
			for (int j = 0; j < this.dimensions; j++) {
				System.out.print(this.grid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
}
