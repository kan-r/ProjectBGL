package com.kan.bglcc.simulation.executor;

import com.kan.bglcc.simulation.exception.Messages;
import com.kan.bglcc.simulation.core.Controller;
import com.kan.bglcc.simulation.exception.InvalidCommandException;

public class CommandExecutorFactory {
	
	private final Controller controller;
	
	
	public CommandExecutorFactory(Controller controller) {
		super();
		this.controller = controller;
	}


	public CommandExecutor getCommandExecutor(String command) throws InvalidCommandException {
		
		if(command == null) {
			throw new InvalidCommandException(Messages.COMMAND_NOTNULL_MSG);
		}
		
		if(command.isBlank()) {
			throw new InvalidCommandException(Messages.COMMAND_NOTBLANK_MSG);
		}
		
		CommandExecutor commandExecutor = null;
		
		if(command.trim().toUpperCase().startsWith("PLACE")) {
			commandExecutor = PlaceCommandExecutor.getInstance(controller);
		} else {
			commandExecutor = SimpleCommandExecutor.getInstance(controller);
		}
		
		return commandExecutor;
	}
}
