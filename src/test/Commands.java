package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Commands {

	public TimeSeries TSanomalyTrain;
	public TimeSeries TSanomalyTest;
	public TimeSeries TSanomalyFile;
	List<AnomalyReport> anomalyReport = new LinkedList<>();

	// Default IO interface
	public interface DefaultIO{
		public String readText();
		public void write(String text);
		public float readVal();
		public void write(float val);

		// you may add default methods here
	}
	
	// the default IO to be used in all commands
	DefaultIO dio;
	public Commands(DefaultIO dio) {
		this.dio=dio;
	}
	
	// you may add other helper classes here
	
	
	
	// the shared state of all commands
	private class SharedState{
		// implement here whatever you need
		
	}
	
	private  SharedState sharedState=new SharedState();

	
	// Command abstract class
	public abstract class Command{
		protected String description;
		
		public Command(String description) {
			this.description=description;
		}
		
		public abstract void execute();
	}



	public class UploadCSVfile extends Command{

		public UploadCSVfile() {

			super("1. upload a time series csv file");
		}

		@Override
		public void execute() {
			dio.write("Please upload your local train CSV file.\n");
			try{
				FileWriter anomalyTrain = new FileWriter("anomalyTrain.csv");
				generateAnomalyCsv(anomalyTrain);

				anomalyTrain.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}

			dio.write("Upload complete.\n");

			dio.write("Please upload your local test CSV file.\n");
			try{
				FileWriter anomalyTest = new FileWriter("anomalyTest.csv");;
				generateAnomalyCsv(anomalyTest);

				anomalyTest.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}

			dio.write("Upload complete.\n");

			TSanomalyTrain = new TimeSeries("anomalyTrain.csv");
			TSanomalyTest = new TimeSeries("anomalyTest.csv");

		}
	}

	public void generateAnomalyCsv (FileWriter f){
		String str = "";
		str = dio.readText();
		while(!str.equals("done")) {
			if(!str.equals("")) {
				try {
					f.write(str + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			str = dio.readText();
		}
	}



	public class AlgorithmSettings extends Command{

		public AlgorithmSettings() {
			super("2. algorithm settings");
		}

		@Override
		public void execute() {
			float newThreshold;
			dio.write("The current correlation threshold is " + TSanomalyTest.threshold + "\n");
			dio.write("Type a new threshold\n");
			newThreshold = dio.readVal();
			while(newThreshold <0 || newThreshold>1 ){
				dio.write("please choose a value between 0 and 1.\nType a new threshold\n");
				newThreshold = dio.readVal();
			}
			TSanomalyTrain.threshold = newThreshold;
			TSanomalyTest.threshold = newThreshold;


		}
	}



	public class DetectAnomalies extends Command{

		public DetectAnomalies() {
			super("3. detect anomalies");
		}

		@Override
		public void execute() {
			SimpleAnomalyDetector simpleAnomalyDetector = new SimpleAnomalyDetector();
			simpleAnomalyDetector.learnNormal(TSanomalyTrain);
			anomalyReport = simpleAnomalyDetector.detect(TSanomalyTest);
			dio.write("anomaly detection complete.\n");

		}
	}



	public class DisplayResults extends Command{

		public DisplayResults() {
			super("4. display results");
		}

		@Override
		public void execute() {
			for (AnomalyReport a: anomalyReport) {
				dio.write(a.timeStep + "\t" + a.description + "\n" );
			}
			dio.write("Done.\n");

		}
	}



	public class UploadAnomaliesAnalyzeResults extends Command{

		public UploadAnomaliesAnalyzeResults() {
			super("5. upload anomalies and analyze results");
		}

		@Override
		public void execute() {
			dio.write("Please upload your local anomalies file.\n");
			// save the client's anomaly file
			FileWriter anomalyFile;
			try{
				anomalyFile = new FileWriter("anomalyFile.csv");
				anomalyFile.write("startAnomaly,endAnomaly\n"); // to fill the criteria titles of thr datamatrix
				generateAnomalyCsv(anomalyFile);
				anomalyFile.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}

			dio.write("Upload complete.\n");

			// save the anomaly detection with start and end timestep in a map
			HashMap<AnomalyReport, Long> detectorAnomalyMap = new HashMap<>();
			AnomalyReport tempAnomalyReport = anomalyReport.get(0);

			for (AnomalyReport a: anomalyReport) {
				if(!a.description.equals(tempAnomalyReport.description)){
					detectorAnomalyMap.put(tempAnomalyReport, a.timeStep-1);
					tempAnomalyReport = a;
				}
			}

			TSanomalyFile = new TimeSeries("anomalyFile.csv");
			try {
				TSanomalyFile.readCsvFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			float[][] anomalyFileMatrix = TSanomalyFile.getDataMatrix();
			
			//find true positive rate


			//compare between galay and received kovetz
			//TODO: continue compare the anomaly file from client, and our detector anomaly map


		}
	}



	public class Exit extends Command{

		public Exit() {
			super("6. exit");
		}

		@Override
		public void execute() {
			dio.write(description);
		}
	}
	

	
}
