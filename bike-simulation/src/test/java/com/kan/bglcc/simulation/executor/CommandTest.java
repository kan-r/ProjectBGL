package com.kan.bglcc.simulation.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.kan.bglcc.simulation.exception.InvalidCommandException;
import com.kan.bglcc.simulation.exception.Messages;

class CommandTest {

	@Test
	void testToCommand() throws InvalidCommandException {
		assertEquals(Command.PLACE, Command.toCommand("PLACE"));
		assertEquals(Command.FORWARD, Command.toCommand("forward"));
		assertEquals(Command.TURN_LEFT, Command.toCommand("TURN_LEFT"));
		assertEquals(Command.TURN_RIGHT, Command.toCommand("Turn_RIGHT"));
		assertEquals(Command.GPS_REPORT, Command.toCommand("GPS_REPORT"));
	}
	
	
	@Test
	void testToCommand_Null() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			Command.toCommand(null);
	    });
		
		assertEquals(Messages.COMMAND_NOTNULL_MSG, exception.getMessage());
		
	}
	
	
	@Test
	void testToCommand_Blank() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			Command.toCommand("");
	    });
		
		assertEquals(Messages.COMMAND_NOTBLANK_MSG, exception.getMessage());
	}
	
	
	@Test
	void testToCommand_Invalid() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			Command.toCommand("UNKNOWN");
	    });
		
		assertEquals(Messages.COMMAND_INVALID_MSG, exception.getMessage());
	}

}
