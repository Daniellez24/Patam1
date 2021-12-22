package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import test.Commands.Command;
import test.Commands.DefaultIO;

public class CLI {

	HashMap<Integer, Command> commands;
	DefaultIO dio;
	Commands c;
	
	public CLI(DefaultIO dio) {
		this.dio=dio;
		c=new Commands(dio); 
		commands=new HashMap<Integer, Command>();
		commands.put(1,c.new UploadCSVfile());
		commands.put(2,c.new AlgorithmSettings());
		commands.put(3,c.new DetectAnomalies());
		commands.put(4,c.new DisplayResults());
		commands.put(5,c.new UploadAnomaliesAnalyzeResults());
		commands.put(6,c.new Exit());


	}
	
	public void start() {
		boolean temp = true;
		while(temp) { // keep it like this? how exit is done?
			// Print menu
			dio.write("Welcome to the Anomaly Detection Server.\nPlease choose an option:\n");
			for (int i=1; i<= commands.size(); i++) {
				dio.write(commands.get(i).description + "\n");
			}
			// asking for the client's number choice and execute
			commands.get((int)dio.readVal()).execute();

			temp=false;
		}



	}
}
