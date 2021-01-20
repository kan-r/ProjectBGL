package com.kan.bglcc.simulation.core;

import com.kan.bglcc.simulation.exception.Messages;
import com.kan.bglcc.simulation.exception.InvalidCommandException;

public enum Direction {
	NORTH, 
	SOUTH, 
	EAST, 
	WEST
	;
	
	
	public static Direction toDirection(String direction) throws InvalidCommandException {
		
		if(direction == null) {
			throw new InvalidCommandException(Messages.DIRECTION_NOTNULL_MSG);
		}
		
		if(direction.isBlank()) {
			throw new InvalidCommandException(Messages.DIRECTION_NOTBLANK_MSG);
		}
		
		try {
			return Direction.valueOf(direction.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new InvalidCommandException(Messages.DIRECTION_INVALID_MSG);
		}
	}
}
