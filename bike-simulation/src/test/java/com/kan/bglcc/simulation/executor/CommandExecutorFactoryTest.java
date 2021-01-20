package com.kan.bglcc.simulation.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

class CommandExecutorFactoryTest {
	
	CommandExecutorFactory commandExecutorFactory;

	@BeforeEach
	void setUp() throws Exception {
		Controller controller = new BikeController(new Grid(7, 7), new Bike(new Position(-1, -1), Direction.NORTH));
		commandExecutorFactory = new CommandExecutorFactory(controller);
	}

	@AfterEach
	void tearDown() throws Exception {
		commandExecutorFactory = null;
	}

	@Test
	void testGetCommandExecutor() throws InvalidCommandException {
		assertTrue(commandExecutorFactory.getCommandExecutor("PLACE") instanceof PlaceCommandExecutor);
		assertTrue(commandExecutorFactory.getCommandExecutor("PLACED") instanceof PlaceCommandExecutor);
		assertTrue(commandExecutorFactory.getCommandExecutor("FORWARD") instanceof SimpleCommandExecutor);
	}
	
	
	@Test
	void testGetCommandExecutor_Null_Command() throws InvalidCommandException {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			commandExecutorFactory.getCommandExecutor(null);
	    });
		
		assertEquals(Messages.COMMAND_NOTNULL_MSG, exception.getMessage());
	}
	
	
	@Test
	void testGetCommandExecutor_Blank_Command() throws InvalidCommandException {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			commandExecutorFactory.getCommandExecutor("");
	    });
		
		assertEquals(Messages.COMMAND_NOTBLANK_MSG, exception.getMessage());
	}

}
