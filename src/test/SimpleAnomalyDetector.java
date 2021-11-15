package test;

import java.util.List;

public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {
	

	@Override
	public void learnNormal(TimeSeries ts) {
		float[][] data = ts.getDataMatrix();
		int N = data.length; // num of columns

		for(int i = 0; i<N; i++){
			float m = 0;
			float c = -1;
			float p;
			for(int j = i+1; j<N; j++){
				if ((p=Math.abs(StatLib.pearson(data[i],data[j]))) > m){ // HAVE TO MAKE A FUNCTION IN TIMESERIES FOR GETTING A COLUMN i AND j
					m=p;
					c=j;
				}
			}
			if(c != (-1)){
				// Fi and Fj are correlated features
			}
		}
	}


	@Override
	public List<AnomalyReport> detect(TimeSeries ts) {
		return null;
	}
	
	public List<CorrelatedFeatures> getNormalModel(){
		return null;
	}
}
