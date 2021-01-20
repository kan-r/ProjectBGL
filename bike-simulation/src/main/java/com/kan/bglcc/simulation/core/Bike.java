package com.kan.bglcc.simulation.core;

public class Bike {
	
	private Position position;
	private Direction direction;
	
	
	public Bike(Position position, Direction direction) {
		super();
		this.position = position;
		this.direction = direction;
	}

	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	public void moveTo(int x, int y) {
		
		if(position == null) {
			return;
		}
		
		position.setX(x);
		position.setY(y);
	}
	
	
	public void turnLeft() {
		
		if(direction == null) {
			return;
		}
		
		switch (direction) {
			case NORTH -> {
				direction = Direction.WEST;
			}
			case SOUTH -> {
				direction = Direction.EAST;
			}
			case EAST -> {
				direction = Direction.NORTH;
			}
			case WEST -> {
				direction = Direction.SOUTH;
			}
		}
	}
	
	
	public void turnRight() {
		
		if(direction == null) {
			return;
		}
	
		switch (direction) {
			case NORTH -> {
				direction = Direction.EAST;
			}
			case SOUTH -> {
				direction = Direction.WEST;
			}
			case EAST -> {
				direction = Direction.SOUTH;
			}
			case WEST -> {
				direction = Direction.NORTH;
			}
		}
	}
	
}
