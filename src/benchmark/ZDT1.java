package benchmark;

import basic.Individual;
import basic.Params;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ZDT1 extends Problem{
    public ZDT1(){
        n=30;
        this.UB = new double[n];
        this.LB = new double[n];
        Arrays.fill(this.LB,0);
        Arrays.fill(this.UB,1);
    }

    public double calF1(double[] x){
        Params.countEvals++;
        return x[0] * (this.UB[0] - this.LB[0]) + this.LB[0];
    }
    public double calF2(double[] x){

        double[] de_normalized = new double[this.n]; //Dãy số thu được sau khi mã hóa NST từ không gian chung ra không gian riêng
        for (int i = 0; i < this.n; i++) {
            de_normalized[i] = x[i] * (this.UB[i] - this.LB[i]) + this.LB[i];
        }
        double gx = calG(x);
        return gx*(1-Math.sqrt(calF1(x)/gx));
    }
    public double calG(double[] x){
        double[] de_normalized = new double[this.n]; //Dãy số thu được sau khi mã hóa NST từ không gian chung ra không gian riêng
        for (int i = 0; i < this.n; i++) {
            de_normalized[i] = x[i] * (this.UB[i] - this.LB[i]) + this.LB[i];
        }

        double sum =0;
        for(int i=1;i < this.n;i++){
            sum+=de_normalized[i];
        }
        return 1+9*sum/(this.n-1);
    }
}
