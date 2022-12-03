package benchmark;

import basic.Individual;

import java.util.Arrays;

import static java.lang.Math.*;

public class DTLZ1 extends Problem{
    private int K = 5;
    public DTLZ1(){
        n=7;
        countObj = 3;
        this.UB = new double[n];
        this.LB = new double[n];
        Arrays.fill(this.LB,0);
        Arrays.fill(this.UB,1);
    }

    public void calFitness(Individual x){
        double temp = 0;
        for(int i=0; i<n; i++){
            x.chromosome[i] = max(0.0, min(x.chromosome[i], 1.0));
        }
        for(int i=2; i<n; i++){
            temp += ((x.chromosome[i]-0.5)*(x.chromosome[i]-0.5)) - cos(20* PI*(x.chromosome[i] - 0.5));
        }
        double g = 100*(K + temp);
        x.fitness[0] = 0.5*x.chromosome[0]*x.chromosome[1]*(1+g);
        x.fitness[1] = 0.5*x.chromosome[0]*(1-x.chromosome[1])*(1+g);
        x.fitness[2] = 0.5*(1-x.chromosome[0])*(1+g);
    }
}
