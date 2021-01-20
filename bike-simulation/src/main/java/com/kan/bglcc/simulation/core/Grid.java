package com.kan.bglcc.simulation.core;

public class Grid {
	
	private final int rows;
	private final int columns;
	
	
	public Grid(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
	}
	

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	
	public boolean isCellOnGrid(int x, int y) {
		
		if(x < 0 || x > columns - 1 || y < 0 || y > rows - 1) {
			return false;
		}
		
		return true;
	}
	
}
