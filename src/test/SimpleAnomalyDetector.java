package test;

import java.util.LinkedList;
import java.util.List;

public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {

	List<CorrelatedFeatures> correlatedFeatureslist = new LinkedList<CorrelatedFeatures>();

	@Override
	public void learnNormal(TimeSeries ts) {
		float[][] data = ts.getDataMatrix();

		int N = data.length; // num of columns
		System.out.println(N);

		for(int i = 0; i<N; i++){
			float m = 0; // current pearson of 2 columns
			int c = -1; // current potential correlated column to column i
			float p; // pearson
			for(int j = i+1; j<data[0].length; j++){ // data[0].length = length of a line (num of criteria/columns)
				System.out.println("i " + i + " j " + j);
				if ((p=Math.abs(StatLib.pearson(ts.getColumn(i),ts.getColumn(j)))) > m){ // compare column i and j
					m=p;
					c=j;
				}
			}
			if(c != (-1) && m > ts.treshold){ // Fi and Fj are correlated features
				System.out.println("Correlated Features: i " + i + " c " + c);
				Point[] points = getPointsArray(ts.getColumn(i), ts.getColumn(c));
				Line l = StatLib.linear_reg(points);
				float maxDev = 0;
				for(int d = 0; d < points.length; d++){ // get the maxDev of the correlated features
					float tempDev = StatLib.dev(points[d], l);
					if (tempDev > maxDev)
						maxDev = tempDev;
				}
				// set the 2 features as correlated,  and add to the correlated features list
				CorrelatedFeatures cr = new CorrelatedFeatures(
						ts.getCriteriaTitle(i),
						ts.getCriteriaTitle(c),
						m,
						l,
						(float) (maxDev*1.1)); // *1.1 to avoid elimination of border points
				correlatedFeatureslist.add(cr);
			}
		}
	}

	public Point[] getPointsArray(float[] a, float[] b){
		Point[] points  = new Point[a.length];
		Point p;
		for (int i = 0; i < a.length; i++){
			p = new Point(a[i], b[i]);
			points[i] = p;
		}
		return points;
	}


	@Override
	public List<AnomalyReport> detect(TimeSeries ts) {

		return null;
	}
	
	public List<CorrelatedFeatures> getNormalModel(){

		return correlatedFeatureslist;
	}
}
