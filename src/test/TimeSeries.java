package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class TimeSeries {

	private String fileName;
	private float[][] dataMatrix;
	public String[] criteriaTitles;
	private int numOfLines;
	public float threshold = (float) 0.9;

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
		numOfLines = getNumOfLines();

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

	public int getNumOfLines () throws Exception{
		BufferedReader buf = new BufferedReader(new FileReader((this.fileName)));
		int counter = 0; // the function will return one line more than reality
		while((buf.readLine()) != null){
			counter++;
		}
		return counter-1; // remove the titles line in the file
	}

	public int getNumOFlinesParameter (){
		return numOfLines;
	}

	public float[][] getDataMatrix(){
		return dataMatrix;
	}

	public String getCriteriaTitle(int index){ // get a name of a feature (criteria)
		return criteriaTitles[index];
	}

	public float[] getColumn(int c){ // c = which column number I'd like to get
		float[] column = new float[numOfLines];
		for (int i = 0; i<numOfLines; i++){
			column[i] = dataMatrix[i][c];
		}

		return column;
	}

}
