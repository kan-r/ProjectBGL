package com.kan.bglcc.simulation.executor;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kan.bglcc.simulation.core.Bike;
import com.kan.bglcc.simulation.core.BikeController;
import com.kan.bglcc.simulation.core.Controller;
import com.kan.bglcc.simulation.core.Direction;
import com.kan.bglcc.simulation.core.Grid;
import com.kan.bglcc.simulation.core.Position;
import com.kan.bglcc.simulation.exception.InvalidCommandException;
import com.kan.bglcc.simulation.exception.Messages;

class SimpleCommandExecutorTest {

	private SimpleCommandExecutor simpleCommandExecutor;
	
	@BeforeEach
	void setUp() throws Exception {
		Controller controller = new BikeController(new Grid(7, 7), new Bike(new Position(0, 0), Direction.NORTH));
		simpleCommandExecutor = SimpleCommandExecutor.getInstance(controller);
	}

	@AfterEach
	void tearDown() throws Exception {
		simpleCommandExecutor = null;
	}
	
	//---------------------------------------------------------------------------

	@Test
	void testExecuteCommand() {
		assertDoesNotThrow(() -> simpleCommandExecutor.executeCommand("FORWARD"));
		assertDoesNotThrow(() -> simpleCommandExecutor.executeCommand("TURN_LEFT"));
		assertDoesNotThrow(() -> simpleCommandExecutor.executeCommand("TURN_RIGHT"));
		assertDoesNotThrow(() -> simpleCommandExecutor.executeCommand("TURN_RIGHT"));
		assertDoesNotThrow(() -> simpleCommandExecutor.executeCommand("FORWARD"));
		assertDoesNotThrow(() -> simpleCommandExecutor.executeCommand("GPS_REPORT"));
	}
	
	
	@Test
	void testExecuteCommand_Null_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			simpleCommandExecutor.executeCommand(null);
	    });
		
		assertEquals(Messages.COMMAND_NOTNULL_MSG, exception.getMessage());
	}
	
	
	@Test
	void testExecuteCommand_Blank_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			simpleCommandExecutor.executeCommand("");
	    });
		
		assertEquals(Messages.COMMAND_NOTBLANK_MSG, exception.getMessage());
	}
	
	
	@Test
	void testExecuteCommand_Invalid_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			simpleCommandExecutor.executeCommand("UNKNOWN");
	    });
		
		assertEquals(Messages.COMMAND_INVALID_MSG, exception.getMessage());
	}
	
}
