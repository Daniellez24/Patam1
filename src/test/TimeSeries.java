package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class TimeSeries {

	String fileName;
	String[][] dataMatrix;


	public TimeSeries(String csvFileName) {
		fileName = csvFileName;
	}

	public void readCsvFile() throws Exception {
		boolean isRead = false;
		int numOfCriteria = 0;
		int counter = 1; // represents the lines in the matrix dataNames
		String[] data;
		String delimiter = ",";
		BufferedReader buf = new BufferedReader(new FileReader((this.fileName)));
		String line;

		line = buf.readLine();
		if (line!= null){
			data = line.split(delimiter);
			numOfCriteria = data.length;
			dataMatrix = new String[200][numOfCriteria];
			for (int i = 0; i < numOfCriteria; i++) {
				dataMatrix[0][i] = data[i];
			}
		}


		while((line = buf.readLine()) != null){
			data = line.split(delimiter);

			for (int i=0; i<numOfCriteria; i++){
				dataMatrix[counter][i] = data[i];
			}

			System.out.println(Arrays.toString(data));
			counter++;
		}

		buf.close();
	}
	
}
