package com.kan.bglcc.simulation.executor;

import com.kan.bglcc.simulation.core.Controller;
import com.kan.bglcc.simulation.exception.InvalidCommandException;

public abstract class CommandExecutor {

	protected Controller controller = null;
	
	public abstract String executeCommand(String command) throws InvalidCommandException;
	
}
