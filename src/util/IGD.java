package util;

import static java.lang.System.exit;

public class IGD {
    public static double EuclideanDistance(double[] x, double[] y){
        if(x.length != y.length){
            System.err.print("Lỗi tại EuclideanDistance (2 chuỗi tham số độ dài khác nhau)");
            exit(1);
        }
        double dis = 0;
        for (int i=0;i< x.length;i++){
            dis+= Math.pow(y[i]-x[i],2);
        }
        return Math.sqrt(dis);
    }
}
