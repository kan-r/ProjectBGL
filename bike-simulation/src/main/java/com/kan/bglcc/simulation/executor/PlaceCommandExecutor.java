package com.kan.bglcc.simulation.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kan.bglcc.simulation.core.Controller;
import com.kan.bglcc.simulation.core.Direction;
import com.kan.bglcc.simulation.exception.InvalidCommandException;
import com.kan.bglcc.simulation.exception.Messages;

public class PlaceCommandExecutor extends CommandExecutor {
	
	private static PlaceCommandExecutor placeCommandExecutor;
	
	
	private PlaceCommandExecutor(Controller controller) {
		super();
		this.controller = controller;
	}
	
	
	public static PlaceCommandExecutor getInstance(Controller controller) {
		
		if(placeCommandExecutor == null) {
			placeCommandExecutor = new PlaceCommandExecutor(controller);
		}
		
		return placeCommandExecutor;
	}
	

	@Override
	public String executeCommand(String command) throws InvalidCommandException {
		
		String ret = "";
		
		List<String> commandSplitted = splitPlaceCommand(command);
		
		List<Object> commandValidated = validatePlaceCommand(commandSplitted);
		
		int x = (int) commandValidated.get(1);
		int y = (int) commandValidated.get(2);
		Direction dirn = (Direction) commandValidated.get(3);
		
		controller.place(x, y, dirn);
		
		return ret;
	}
	
	
	public List<String> splitPlaceCommand(String command) throws InvalidCommandException{
		
		if(command == null) {
			throw new InvalidCommandException(Messages.COMMAND_NOTNULL_MSG);
		}
		
		if(command.isBlank()) {
			throw new InvalidCommandException(Messages.COMMAND_NOTBLANK_MSG);
		}
		
		List<String> ret = new ArrayList<>();
		
		// slit command into 2 segments: command and params
		String[] segments = command.trim().split("\s+", 2);
		
		if(segments.length > 0) {
			ret.add(segments[0]);
		}
		
		// split params
		if(segments.length > 1) {
			Arrays.asList(segments[1].split(",")).forEach(v -> {
				ret.add(v.trim());
			});
		}
		
		return ret;
	}
	
	
	public List<Object> validatePlaceCommand(List<String> command) throws InvalidCommandException {
		
		if(command == null) {
			throw new InvalidCommandException(Messages.COMMAND_NOTNULL_MSG);
		}
		
		if(command.size() < 4) {
			throw new InvalidCommandException(Messages.COMMAND_INVALID_MSG);
		}
		
		Command cmd = Command.toCommand(command.get(0));
		
		if(cmd != Command.PLACE) {
			throw new InvalidCommandException(Messages.COMMAND_INVALID_MSG);
		}
		
		if(!isInt(command.get(1))) {
			throw new InvalidCommandException(Messages.X_POSITION_INVALID_MSG);
		}
		
		if(!isInt(command.get(2))) {
			throw new InvalidCommandException(Messages.Y_POSITION_INVALID_MSG);
		}
		
		Direction direction = Direction.toDirection(command.get(3));
		
		List<Object> ret = new ArrayList<>();
		ret.add(cmd);
		ret.add(Integer.valueOf(command.get(1)));
		ret.add(Integer.valueOf(command.get(2)));
		ret.add(direction);
		
		return ret;
	}
	
	
	public boolean isInt(String intStr) {
		
		if(intStr == null) {
			return false;
		}
		
		try {
			Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}
