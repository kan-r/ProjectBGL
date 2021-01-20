package com.kan.bglcc.simulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.kan.bglcc.simulation.core.Bike;
import com.kan.bglcc.simulation.core.BikeController;
import com.kan.bglcc.simulation.core.Controller;
import com.kan.bglcc.simulation.core.Direction;
import com.kan.bglcc.simulation.core.Grid;
import com.kan.bglcc.simulation.core.Position;
import com.kan.bglcc.simulation.exception.InvalidCommandException;
import com.kan.bglcc.simulation.executor.CommandExecutor;
import com.kan.bglcc.simulation.executor.CommandExecutorFactory;

public class BikeSimulationApp {
	
	private static final boolean DEBUG = false;
	
	private static final int GRID_SIZE = 7;
	
	private static CommandExecutorFactory commandExecutorFactory;

	
	public static void main(String[] args) {
		
		inititalise();
		
		if(args.length > 0) {
			executeCommands(args[0]);
		}else {
			executeCommands();
		}
	}
	
	
	private static void inititalise() {
		
		Grid grid = new Grid(GRID_SIZE, GRID_SIZE);
		Bike bike = new Bike(new Position(0, -1), Direction.NORTH);
		Controller controller = new BikeController(grid, bike);
		commandExecutorFactory = new CommandExecutorFactory(controller);
	}

	
	private static void executeCommands() {

		Scanner scanner = new Scanner(System.in);
		
		String line = "";
		
		do {
			
			System.out.println();
			System.out.print("Enter COMMAND ( \\q to quit, \\h for help ): ");
			
			line = scanner.nextLine();
			
			switch (line) {
				case "\\h", "\\H" -> {
					help();
				}
				case "\\q", "\\Q" -> {
					System.out.println();
					System.out.println("Bye!");
				}
				default ->{
					executeCommand(line);
				}
			}
			
		} while (!line.equalsIgnoreCase("\\q"));
		
		scanner.close();
	}

	
	private static void executeCommands(String fileName) {
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(fileName));
			
			lines.forEach(line -> {
				executeCommand(line);
			});
			
		} catch (IOException e) {
			writeOutput("Error in reading the file: " + fileName);
			System.exit(1);
		}
	}
	
	
	private static void executeCommand(String command) {
		
		try {
			CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
			
			String ret = commandExecutor.executeCommand(command);
			
			if(ret != null && !ret.isBlank()) {
				writeOutput(ret);
			}
			
		} catch (InvalidCommandException e) {
			log(e.getMessage(), DEBUG);
		}
	}
	
	
	private static void writeOutput(String output) {
		
		System.out.println("");
		System.out.print("\t");
		System.out.println(output);
	}
	
	
	private static void help() {
		
		System.out.println();
		System.out.println("Available COMMANDS");
		System.out.println("--------------------------------");
		System.out.println("PLACE <X>,<Y>,<Facing-direction>");
		System.out.println("FORWARD");
		System.out.println("TURN_LEFT");
		System.out.println("TURN_RIGHT");
		System.out.println("GPS_REPORT");
	}
	
	
	private static void log(String message, boolean debug) {
		
		if(debug) {
			System.out.println();
			System.out.print("\t");
			System.out.println(message);
		}
	}
}
