package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class TimeSeries {

	String fileName;


	public TimeSeries(String csvFileName) {
		fileName = csvFileName;
	}

	public void readCsvFile() throws Exception {
		String[][] dataMatrix = new String[200][];
		int numOfColumns = 0;
		int counter = 1; // represents the lines in the matrix dataNames
		String[] data;
		String delimiter = ",";
		BufferedReader buf = new BufferedReader(new FileReader((this.fileName)));
		String line;
		int numOfLines = getNumOfLines(fileName);

		line = buf.readLine();
		if (line!= null){
			data = line.split(delimiter);
			numOfColumns = data.length;
			dataMatrix = new String[numOfLines][numOfColumns];
			for (int i = 0; i < numOfColumns; i++) {
				dataMatrix[0][i] = data[i];
			}
		}

		while((line = buf.readLine()) != null){
			data = line.split(delimiter);
			for (int i=0; i<numOfColumns; i++){
				dataMatrix[counter][i] = data[i];
			}
			counter++;
		}
		System.out.println(Arrays.toString(dataMatrix));

		buf.close();
	}

	public int getNumOfLines (String fileName) throws Exception{
		BufferedReader buf = new BufferedReader(new FileReader((this.fileName)));
		int counter = 0; // the function will return one line more than reality
		while((buf.readLine()) != null){
			counter++;
		}
		return counter;
	}
	
}
