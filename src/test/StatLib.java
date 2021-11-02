package test;

import test.Line;

public class StatLib {


    // simple average
    public static float avg(float[] x) {
        int xLength = x.length;
        float sum = 0;
        for (int i = 0; i < xLength; i++)
            sum = sum + x[i];
        float avg = sum / xLength;
        return avg;
    }

    // returns the variance of X and Y
    public static float var(float[] x) {
        float N = x.length;
        float sum = 0;
        float u = avg(x);

        for (int i = 0; i < N; i++)
            sum = sum + (x[i] * x[i]);

        float var = ((1 / N) * sum) - (u * u);
        return var;

    }

    // returns the covariance of X and Y
    public static float cov(float[] x, float[] y) {
        int N = x.length;
        float sum = 0;
        for (int i = 0; i < N; i++) // do we know for sure that size of x equals size of y? add test?
            sum = sum + ((x[i] - avg(x)) * (y[i] - avg(y)));
        float cov = sum / N;
        return cov;
    }


    // returns the Pearson correlation coefficient of X and Y
    public static float pearson(float[] x, float[] y) {
        float p1 = cov(x,y);
        float varX = var(x);
        float varY = var(y);
        float Sx = (float)Math.sqrt(varX);
        float Sy = (float)Math.sqrt(varY);
        float pear = p1/(Sx*Sy);

        return pear;
    }

    // performs a linear regression and returns the line equation
    public static Line linear_reg(Point[] points) {
        float[] x = new float[points.length]; // organize points in X and Y arrays
        float[] y = new float[points.length];

        for (int i=0; i< points.length; i++){
            x[i] = points[i].x;
            y[i] = points[i].y;
        }

        float a = cov(x,y)/var(x);
        float b = avg(y) - a*avg(x);

        Line l = new Line(a,b);

        return l;

    }

    // returns the deviation between point p and the line equation of the points
    public static float dev(Point p, Point[] points) {
        Line l = linear_reg(points);
        float dev = Math.abs(l.f(p.x) - p.y);

        return dev;
    }

    // returns the deviation between point p and the line
    public static float dev(Point p, Line l) {
        float dev = Math.abs(l.f(p.x) - p.y);
        return dev;
    }

}