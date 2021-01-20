package com.kan.bglcc.simulation.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kan.bglcc.simulation.exception.InvalidCommandException;
import com.kan.bglcc.simulation.exception.Messages;

class DirectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testToDirection() throws InvalidCommandException {
		assertEquals(Direction.NORTH, Direction.toDirection("NORTH"));
		assertEquals(Direction.SOUTH, Direction.toDirection("South"));
		assertEquals(Direction.EAST, Direction.toDirection("EAST"));
		assertEquals(Direction.WEST, Direction.toDirection("west"));
	}
	
	
	@Test
	void testToDirection_Null() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			Direction.toDirection(null);
	    });
		
		assertEquals(Messages.DIRECTION_NOTNULL_MSG, exception.getMessage());
	}
	
	
	@Test
	void testToDirection_Blank() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			Direction.toDirection("");
	    });
		
		assertEquals(Messages.DIRECTION_NOTBLANK_MSG, exception.getMessage());
	}
	
	
	@Test
	void testToDirection_Invalid() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			Direction.toDirection("UNKNOWN");
	    });
		
		assertEquals(Messages.DIRECTION_INVALID_MSG, exception.getMessage());
	}

}
