package com.kan.bglcc.simulation.core;

public class BikeController implements Controller {

	private final Grid grid;
	private final Bike bike;
	
	
	public BikeController(Grid grid, Bike bike) {
		super();
		this.grid = grid;
		this.bike = bike;
	}
	
	
	public Grid getGrid() {
		return grid;
	}

	public Bike getBike() {
		return bike;
	}
	
	
	public boolean isBikeOnGrid() {
		
		if(grid == null || bike == null || bike.getPosition() == null) {
			return false;
		}
		
		int x = bike.getPosition().getX();
		int y = bike.getPosition().getY();
		
		return grid.isCellOnGrid(x, y);
	}
	
	
	@Override
	public boolean place(int x, int y, Direction direction) {
		
		if(grid == null || bike == null) {
			return false;
		}
		
		if(!grid.isCellOnGrid(x, y)) {
			return false;
		}
		
		bike.moveTo(x, y);
		bike.setDirection(direction);
		
		return true;
	}
	

	@Override
	public boolean moveForward() {
		
		if(grid == null || bike == null || bike.getPosition() == null || bike.getDirection() == null) {
			return false;
		}
		
		if(!isBikeOnGrid()) {
			return false;
		}
		
		int x = bike.getPosition().getX();
		int y = bike.getPosition().getY();
		
		Direction direction = bike.getDirection();
		
		switch (direction) {
			case NORTH -> {
				y++;
			}
			case SOUTH -> {
				y--;
			}
			case EAST -> {
				x++;
			}
			case WEST -> {
				x--;
			}
		}
		
		if(!grid.isCellOnGrid(x, y)) {
			return false;
		}
		
		bike.getPosition().setX(x);
		bike.getPosition().setY(y);
		
		return true;
	}

	
	@Override
	public boolean turnLeft() {
		
		if(!isBikeOnGrid()) {
			return false;
		}
		
		bike.turnLeft();
		
		return true;
	}

	
	@Override
	public boolean turnRight() {
		
		if(!isBikeOnGrid()) {
			return false;
		}
		
		bike.turnRight();
		
		return true;
	}

	
	@Override
	public String getGpsReport() {
		
		if(!isBikeOnGrid()) {
			return "";
		}
		
		return bike.getPosition() + ", " + bike.getDirection();
	}
}
