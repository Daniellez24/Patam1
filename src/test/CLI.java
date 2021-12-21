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

		// implement
	}
	
	public void start() {
		while(true) { // keep it like this? how exit is done?
			dio.write("Welcome to the Anomaly Detection Server.\nPlease choose an option:");
			int i = 1;
			for (Command c : commands) {
				dio.write(i + ". ");
				c.execute();
				//			System.out.println(i + ". " + c.execute());
				i++;
			}
		}



	}
}
