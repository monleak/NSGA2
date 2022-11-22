package benchmark;

import basic.Individual;
import basic.Params;

public abstract class Problem {
    public double[] UB;
    public double[] LB;
    public int n;

    public abstract double calF1(double[] x);
    public abstract double calF2(double[] x);
    public abstract double calG(double[] x);

}
