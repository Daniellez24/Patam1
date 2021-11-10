package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class TimeSeries {

	String fileName;
	String[] flightParameters;

	public TimeSeries(String csvFileName) {
		fileName = csvFileName;
	}

	public void readCsvFile() throws Exception {
		String delimiter = ",";
		BufferedReader buf = new BufferedReader(new FileReader((this.fileName)));
		String line;
		while((line = buf.readLine()) != null){
			String[] data = line.split(delimiter);
			if(flightParameters == null)
				flightParameters = data;

			System.out.println(Arrays.toString(data));
		}
		buf.close();
	}
	
}
