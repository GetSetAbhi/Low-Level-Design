package com.demo.party;

import java.util.HashMap;
import java.util.Map;

public class ScoreManager {
	
	private Map<Integer, Integer> rowScore;
	private Map<Integer, Integer> columnScore;
	private int posDiagonalScore;
	private int negDiagonalScore;
	private int dimensions;

	public ScoreManager(int dimensions) {
		this.dimensions = dimensions;
		this.rowScore = new HashMap<>();
		this.columnScore = new HashMap<>();
		this.posDiagonalScore = 0;
		this.negDiagonalScore = 0;
	}

	public void markMove(int x, int y) {
		int oldRowScore = this.rowScore.getOrDefault(x, 0);
		this.rowScore.put(x, oldRowScore + 1);
		
		int oldColumnScore = this.columnScore.getOrDefault(y, 0);
		this.columnScore.put(y, oldColumnScore + 1);
		
		if (x + y == this.dimensions - 1) {
			this.posDiagonalScore += 1;
		}
		
		if (x - y == 0) {
			this.negDiagonalScore += 1;
		}
	}

	public boolean isWinner(int x, int y) {
		if (this.rowScore.getOrDefault(x, 0) == this.dimensions) {
			return true;
		}
		if (this.columnScore.getOrDefault(y, 0) == this.dimensions) {
			return true;
		}
		if (this.posDiagonalScore == this.dimensions || this.negDiagonalScore == this.dimensions) {
			return true;
		}
		return false;
	}

	
}
