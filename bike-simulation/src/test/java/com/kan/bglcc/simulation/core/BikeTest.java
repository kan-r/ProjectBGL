package com.kan.bglcc.simulation.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BikeTest {
	
	private Bike bike;
	private Position position;
	private Direction direction;

	@BeforeEach
	void setUp() throws Exception {
		
		position = new Position(0, 0);
		direction = Direction.NORTH;
		
		bike = new Bike(position, direction);
	}

	@AfterEach
	void tearDown() throws Exception {
		bike = null;
	}

	@Test
	void testGetPosition() {
		assertEquals(new Position(0, 0), bike.getPosition());;
	}

	@Test
	void testSetPosition() {
		bike.setPosition(new Position(1, 1));
		assertEquals(new Position(1, 1), bike.getPosition());
	}
	
	@Test
	void testSetPosition_Null() {
		bike.setPosition(null);
		assertEquals(null, bike.getPosition());
	}

	@Test
	void testGetDirection() {
		assertEquals(direction, bike.getDirection());
	}

	@Test
	void testSetDirection() {
		bike.setDirection(Direction.SOUTH);
		assertEquals(Direction.SOUTH, bike.getDirection());
	}
	
	@Test
	void testSetDirection_Null() {
		bike.setDirection(null);
		assertEquals(null, bike.getDirection());
	}

	@Test
	void testMoveTo() {
		bike.moveTo(1, 2);
		assertEquals(new Position(1, 2), bike.getPosition());
		
		bike.moveTo(1, 7);
		assertEquals(new Position(1, 7), bike.getPosition());
		
		bike.moveTo(-1, -1);
		assertEquals(new Position(-1, -1), bike.getPosition());
		
		bike.moveTo(6, 6);
		assertEquals(new Position(6, 6), bike.getPosition());
	}
	
	@Test
	void testTurnLeft() {
		bike.setDirection(Direction.NORTH);
		
		bike.turnLeft();
		assertEquals(Direction.WEST, bike.getDirection());
		
		bike.turnLeft();
		assertEquals(Direction.SOUTH, bike.getDirection());
		
		bike.turnLeft();
		assertEquals(Direction.EAST, bike.getDirection());
		
		bike.turnLeft();
		assertEquals(Direction.NORTH, bike.getDirection());
	}
	
	@Test
	void testTurnRight() {
		bike.setDirection(Direction.NORTH);
		
		bike.turnRight();
		assertEquals(Direction.EAST, bike.getDirection());
		
		bike.turnRight();
		assertEquals(Direction.SOUTH, bike.getDirection());
		
		bike.turnRight();
		assertEquals(Direction.WEST, bike.getDirection());
		
		bike.turnRight();
		assertEquals(Direction.NORTH, bike.getDirection());
	}
}
