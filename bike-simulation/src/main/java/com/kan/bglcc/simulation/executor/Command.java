package com.kan.bglcc.simulation.executor;

import com.kan.bglcc.simulation.exception.Messages;
import com.kan.bglcc.simulation.exception.InvalidCommandException;

public enum Command {
	PLACE, 
	FORWARD, 
	TURN_LEFT, 
	TURN_RIGHT, 
	GPS_REPORT
	;
	
	
	public static Command toCommand(String command) throws InvalidCommandException {
		
		if(command == null) {
			throw new InvalidCommandException(Messages.COMMAND_NOTNULL_MSG);
		}
		
		if(command.isBlank()) {
			throw new InvalidCommandException(Messages.COMMAND_NOTBLANK_MSG);
		}
		
		try {
			return Command.valueOf(command.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new InvalidCommandException(Messages.COMMAND_INVALID_MSG);
		}
	}
}
