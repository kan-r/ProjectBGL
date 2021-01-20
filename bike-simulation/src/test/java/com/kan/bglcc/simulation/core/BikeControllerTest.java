package com.kan.bglcc.simulation.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BikeControllerTest {
	
	private BikeController bikeController;
	private Grid grid;
	private Bike bike;

	@BeforeEach
	void setUp() throws Exception {
		grid = new Grid(7, 7);
		bike = new Bike(new Position(-1, -1), Direction.NORTH);
		bikeController = new BikeController(grid, bike);
	}

	@AfterEach
	void tearDown() throws Exception {
		bikeController = null;
	}

	@Test
	void testGetGrid() {
		assertEquals(grid, bikeController.getGrid());
	}

	@Test
	void testGetBike() {
		assertEquals(bike, bikeController.getBike());
	}
	
	@Test
	void testPlace() {
		bikeController.place(1, 2, Direction.NORTH);
		assertEquals(new Position(1, 2), bikeController.getBike().getPosition());
		assertEquals(Direction.NORTH, bikeController.getBike().getDirection());
		
		bikeController.place(1, 7, Direction.SOUTH);
		assertEquals(new Position(1, 2), bikeController.getBike().getPosition());
		assertEquals(Direction.NORTH, bikeController.getBike().getDirection());
		
		bikeController.place(-1, -1, Direction.EAST);
		assertEquals(new Position(1, 2), bikeController.getBike().getPosition());
		assertEquals(Direction.NORTH, bikeController.getBike().getDirection());
		
		bikeController.place(6, 6, Direction.WEST);
		assertEquals(new Position(6, 6), bikeController.getBike().getPosition());
		assertEquals(Direction.WEST, bikeController.getBike().getDirection());
	}

	@Test
	void testMoveForward() {
		bikeController.moveForward();
		assertEquals(new Position(-1, -1), bikeController.getBike().getPosition());
		
		bikeController.place(0, 0, Direction.NORTH);
		
		bikeController.moveForward();
		assertEquals(new Position(0, 1), bikeController.getBike().getPosition());
		
		bikeController.getBike().setDirection(Direction.SOUTH);
		bikeController.moveForward();
		assertEquals(new Position(0, 0), bikeController.getBike().getPosition());
		
		bikeController.getBike().setDirection(Direction.EAST);
		bikeController.moveForward();
		assertEquals(new Position(1, 0), bikeController.getBike().getPosition());
		
		bikeController.getBike().setDirection(Direction.WEST);
		bikeController.moveForward();
		assertEquals(new Position(0, 0), bikeController.getBike().getPosition());
	}

	@Test
	void testTurnLeft() {
		bikeController.turnLeft();
		assertEquals(Direction.NORTH, bikeController.getBike().getDirection());
		
		bikeController.place(0, 0, Direction.NORTH);
		
		bikeController.turnLeft();
		assertEquals(Direction.WEST, bikeController.getBike().getDirection());
		
		bikeController.turnLeft();
		assertEquals(Direction.SOUTH, bikeController.getBike().getDirection());
		
		bikeController.turnLeft();
		assertEquals(Direction.EAST, bikeController.getBike().getDirection());
		
		bikeController.turnLeft();
		assertEquals(Direction.NORTH, bikeController.getBike().getDirection());
	}

	@Test
	void testTurnRight() {
		bikeController.turnRight();
		assertEquals(Direction.NORTH, bikeController.getBike().getDirection());
		
		bikeController.place(0, 0, Direction.NORTH);
		
		bikeController.turnRight();
		assertEquals(Direction.EAST, bikeController.getBike().getDirection());
		
		bikeController.turnRight();
		assertEquals(Direction.SOUTH, bikeController.getBike().getDirection());
		
		bikeController.turnRight();
		assertEquals(Direction.WEST, bikeController.getBike().getDirection());
		
		bikeController.turnRight();
		assertEquals(Direction.NORTH, bikeController.getBike().getDirection());
	}

	@Test
	void testGetGpsReport() {
		bikeController.place(1, 2, Direction.NORTH);
		assertEquals("(1,2), NORTH", bikeController.getGpsReport());
		
		bikeController.getBike().moveTo(-1, -1);;
		assertEquals("", bikeController.getGpsReport());
	}
}
