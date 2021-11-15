package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class TimeSeries {

	private String fileName;
	private float[][] dataMatrix;
	private String[] criteriaTitles;

	public TimeSeries(String csvFileName) {
		fileName = csvFileName;
	}

	public void readCsvFile() throws Exception {
		int numOfColumns = 0;
		int counter = 0; // represents the lines in the matrix dataMatrix
		String[] data;
		String delimiter = ",";
		BufferedReader buf = new BufferedReader(new FileReader((this.fileName)));
		String line;
		int numOfLines = getNumOfLines(fileName);

		line = buf.readLine();
		if (line!= null){
			data = line.split(delimiter);
			numOfColumns = data.length;
			dataMatrix = new float[numOfLines][numOfColumns];
			criteriaTitles = new String[numOfColumns];
			for (int i = 0; i < numOfColumns; i++) {
				criteriaTitles[i] = data[i]; // set titles array
			}
		}

		while((line = buf.readLine()) != null){
			data = line.split(delimiter);
			for (int i=0; i<numOfColumns; i++){
				dataMatrix[counter][i] = Float.parseFloat(data[i]);
			}
			counter++;
		}
//		System.out.println(Arrays.toString(dataMatrix));

		buf.close();
	}

	public int getNumOfLines (String fileName) throws Exception{
		BufferedReader buf = new BufferedReader(new FileReader((this.fileName)));
		int counter = 0; // the function will return one line more than reality
		while((buf.readLine()) != null){
			counter++;
		}
		return counter-1; // remove the titles line in the file
	}

	public float[][] getDataMatrix(){
		return dataMatrix;
	}

}
