package test;

import java.awt.*;

public class MainTrain {

    public static boolean wrong(float val, float expected){
        return val<expected-0.001 || val>expected+0.001;
    }

    public static void main(String[] args) {
        final int N=10;
        float x[]={1,2,3,4,5,6,7,8,9,10};
        float y[]={2.1f,4.2f,6.1f,8.1f,10.3f,12.2f,14.4f,16.1f,18.2f,20.3f};

        test.Point ps[]=new test.Point[N];
        for(int i=0;i<N;i++)
            ps[i]=new test.Point(x[i],y[i]);

        test.Line l=test.StatLib.linear_reg(ps);
        test.Point p=new test.Point(4,8);

//        float v[]={test.StatLib.var(x),test.StatLib.cov(x,y),test.StatLib.pearson(x,y),l.a,l.b,l.f(4),test.StatLib.dev(p,l)};
//        float e[]={8.25f,16.63f,0.999f,2.015f,0.113f,8.176f,0.176f};
//
//
//        for(int i=0;i<7;i++)
//            if(wrong(v[i],e[i]))
//                System.out.println("error for check "+i+" (-14)");
//

//        System.out.println("done");
//
//        System.out.println(StatLib.avg(x)); // 5.5
//
//        System.out.println(StatLib.var(x)); // 9.1666. Tamir: 8.25
//
//        System.out.println(StatLib.cov(x,y)); // 18.48. Tamir: 16.630001
//
//        System.out.println(StatLib.pearson(x,y)); // Tamir: 0.9998814



    }

}