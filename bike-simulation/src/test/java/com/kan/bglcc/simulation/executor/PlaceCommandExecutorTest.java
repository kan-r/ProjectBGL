package com.kan.bglcc.simulation.executor;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

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

class PlaceCommandExecutorTest {
	
	private PlaceCommandExecutor placeCommandExecutor;

	@BeforeEach
	void setUp() throws Exception {
		Controller controller = new BikeController(new Grid(7, 7), new Bike(new Position(0, 0), Direction.NORTH));
		placeCommandExecutor = PlaceCommandExecutor.getInstance(controller);
	}

	@AfterEach
	void tearDown() throws Exception {
		placeCommandExecutor = null;
	}
	
	//---------------------------------------------------------------------------

	@Test
	void testExecuteCommand() {
		assertDoesNotThrow(() -> placeCommandExecutor.executeCommand("PLACE 0,1,NORTH"));
		assertDoesNotThrow(() -> placeCommandExecutor.executeCommand("PLACE  1, 0,   NORTH"));
	}
	
	
	@Test
	void testExecuteCommand_Null_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.executeCommand(null);
	    });
		
		assertEquals(Messages.COMMAND_NOTNULL_MSG, exception.getMessage());
	}
	
	
	@Test
	void testExecuteCommand_Blank_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.executeCommand("");
	    });
		
		assertEquals(Messages.COMMAND_NOTBLANK_MSG, exception.getMessage());
	}
	
	
	@Test
	void testExecuteCommand_Invalid_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.executeCommand("PLACED 0,1,NORTH");
	    });
		
		assertEquals(Messages.COMMAND_INVALID_MSG, exception.getMessage());
	}
	
	//---------------------------------------------------------------------------

	@Test
	void testSplitPlaceCommand() throws InvalidCommandException {
		assertEquals(List.of("PLACE", "0", "1", "NORTH"), placeCommandExecutor.splitPlaceCommand("PLACE 0,1,NORTH"));
		assertEquals(List.of("PLACE", "0", "1", "NORTH"), placeCommandExecutor.splitPlaceCommand("PLACE  0, 1,   NORTH"));
		assertEquals(List.of("PLACED", "0", "1", "NORTH"), placeCommandExecutor.splitPlaceCommand("PLACED 0,1,NORTH"));
		assertEquals(List.of("PLACE", "0"), placeCommandExecutor.splitPlaceCommand("PLACE 0"));
		assertEquals(List.of("PLACE"), placeCommandExecutor.splitPlaceCommand("PLACE"));
	}
	
	
	@Test
	void testSplitPlaceCommand_Null_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.splitPlaceCommand(null);
	    });
		
		assertEquals(Messages.COMMAND_NOTNULL_MSG, exception.getMessage());
	}
	
	
	@Test
	void testSplitPlaceCommand_Blank_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.splitPlaceCommand("");
	    });
		
		assertEquals(Messages.COMMAND_NOTBLANK_MSG, exception.getMessage());
	}
	
	//---------------------------------------------------------------------------

	@Test
	void testValidatePlaceCommand() {
		assertDoesNotThrow(() -> placeCommandExecutor.validatePlaceCommand(List.of("PLACE", "0", "1", "NORTH")));
	}
	
	
	@Test
	void testValidatePlaceCommand_Null_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.validatePlaceCommand(null);
	    });
		
		assertEquals(Messages.COMMAND_NOTNULL_MSG, exception.getMessage());
	}
	
	
	@Test
	void testValidatePlaceCommand_Blank_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.validatePlaceCommand(new ArrayList<String>());
	    });
		
		assertEquals(Messages.COMMAND_INVALID_MSG, exception.getMessage());
	}
	
	
	@Test
	void testValidatePlaceCommand_Invalid_Command() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.validatePlaceCommand(List.of("PLACED", "0", "1", "NORTH"));
	    });
		
		assertEquals(Messages.COMMAND_INVALID_MSG, exception.getMessage());
	}
	
	
	@Test
	void testValidatePlaceCommand_Blank_Params() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.validatePlaceCommand(List.of("PLACE",""," ",""));
	    });
		
		assertEquals(Messages.X_POSITION_INVALID_MSG, exception.getMessage());
	}
	
	
	@Test
	void testValidatePlaceCommand_Less_Params() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.validatePlaceCommand(List.of("PLACE", "0", "1"));
	    });
		
		assertEquals(Messages.COMMAND_INVALID_MSG, exception.getMessage());
	}
	
	
	@Test
	void testValidatePlaceCommand_Invalid_X() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.validatePlaceCommand(List.of("PLACE", "a","1", "NORTH"));
	    });
		
		assertEquals(Messages.X_POSITION_INVALID_MSG, exception.getMessage());
	}
	
	
	@Test
	void testValidatePlaceCommand_Invalid_Y() {
		
		Exception exception = assertThrows(InvalidCommandException.class, () -> {
			placeCommandExecutor.validatePlaceCommand(List.of("PLACE", "0"," ", "NORTH"));
	    });
		
		assertEquals(Messages.Y_POSITION_INVALID_MSG, exception.getMessage());
	}
	
	//---------------------------------------------------------------------------

	@Test
	void testIsInt() {
		assertTrue(placeCommandExecutor.isInt("1"));
		assertFalse(placeCommandExecutor.isInt("a"));
		assertFalse(placeCommandExecutor.isInt(""));
		assertFalse(placeCommandExecutor.isInt(null));
	}
}
