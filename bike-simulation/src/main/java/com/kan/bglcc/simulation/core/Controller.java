package com.kan.bglcc.simulation.core;

public interface Controller {

	public boolean place(int x, int y, Direction direction);
	public boolean moveForward();
	public boolean turnLeft();
	public boolean turnRight();
	public String getGpsReport();
	
}
