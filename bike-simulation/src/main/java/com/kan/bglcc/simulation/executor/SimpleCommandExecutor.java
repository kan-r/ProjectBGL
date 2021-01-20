package com.kan.bglcc.simulation.executor;

import com.kan.bglcc.simulation.core.Controller;
import com.kan.bglcc.simulation.exception.InvalidCommandException;
import com.kan.bglcc.simulation.exception.Messages;

public class SimpleCommandExecutor extends CommandExecutor {
	
	private static SimpleCommandExecutor simpleCommandExecutor;
	
	
	protected SimpleCommandExecutor(Controller controller) {
		super();
		this.controller = controller;
	}
	
	
	public static SimpleCommandExecutor getInstance(Controller controller) {
		
		if(simpleCommandExecutor == null) {
			simpleCommandExecutor = new SimpleCommandExecutor(controller);
		}
		 
		return simpleCommandExecutor;
	}

	
	@Override
	public String executeCommand(String command) throws InvalidCommandException {
		
		String ret = "";
		
		Command cmd = Command.toCommand(command);
		
		switch (cmd) {
			case FORWARD -> {
				controller.moveForward();
			}
			case TURN_LEFT -> {
				controller.turnLeft();
			}
			case TURN_RIGHT -> {
				controller.turnRight();
			}
			case GPS_REPORT -> {
				ret = controller.getGpsReport();
			}
			default -> {
				throw new InvalidCommandException(Messages.COMMAND_INVALID_MSG);
			}
		}
		
		return ret;
	}

}
