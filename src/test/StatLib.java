package test;

import test.Line;

public class StatLib {



    // simple average
    public static float avg(float[] x){
        int xLength = x.length;
        float sum = 0;
        for (int i = 0; i<xLength; i++)
            sum = sum + x[i];
        float avg = sum/xLength;
        return avg;
    }

    // returns the variance of X and Y
    public static float var(float[] x){
        int N = x.length;
        float sum = 0;
        float u = avg(x);
        //float u = 0;
        //for(int i = 0; i<N; i++)
        //	u = u + x[i];
        //u = u*(1/N);

        for(int i = 0; i<N; i++)
            sum = sum + (x[i]*x[i]);

        float var = (1/N)*sum - u*u;
        return var;
    }

    // returns the covariance of X and Y
    public static float cov(float[] x, float[] y){
        int N = x.length;
        float sum = 0;
        for(int i = 0; i<N; i++) // do we know for sure that size of x equals size of y? add test?
            sum = sum + ((x[i]-avg(x))*(y[i]-avg(y)));
        float cov = sum/N;
        return cov;
    }


    // returns the Pearson correlation coefficient of X and Y
    public static float pearson(float[] x, float[] y){
        return 0;
    }

    // performs a linear regression and returns the line equation
    public static Line linear_reg(Point[] points){
        return null;
    }

    // returns the deviation between point p and the line equation of the points
    public static float dev(Point p, Point[] points){
        return 0;
    }

    // returns the deviation between point p and the line
    public static float dev(Point p, Line l){
        return 0;
    }

}