package com.kan.bglcc.simulation.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTest {
	
	private Position position;

	@BeforeEach
	void setUp() throws Exception {
		position = new Position(1, 0);
	}

	@AfterEach
	void tearDown() throws Exception {
		position = null;
	}

	@Test
	void testGetX() {
		assertEquals(1, position.getX());
	}

	@Test
	void testSetX() {
		position.setX(5);
		assertEquals(5, position.getX());
	}

	@Test
	void testGetY() {
		assertEquals(0, position.getY());
	}

	@Test
	void testSetY() {
		position.setY(6);
		assertEquals(6, position.getY());
	}

	@Test
	void testEqualsObject() {
		assertTrue(position.equals(new Position(1, 0)));
		assertFalse(position.equals(new Position(0, 1)));
	}

	@Test
	void testToString() {
		assertEquals("(1,0)", position.toString());
	}
}
