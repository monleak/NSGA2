package util;

import java.util.ArrayList;

import basic.Params;

public class SBX {

    /**
     * Simulated binary crossover, each value of generated offspring is normalized
     * into the range [0,1]
     *
     * @param p1
     * @param p2
     * @return
     */
    public static ArrayList<double[]> generateOffspring(double[] p1, double[] p2) {
        int dim = p1.length;
        double cf[] = new double[dim];
        for (int i = 0; i < dim; i++) {
            cf[i] = 1;
            double u = Params.rand.nextDouble();
            if (u <= 0.5) {
                cf[i] = Math.pow((2 * u), 1.0 / (Params.NC + 1));
            } else {
                cf[i] = Math.pow(2 * (1 - u), -1.0 / (Params.NC + 1));
            }
        }

        double[] c1 = new double[dim];
        double[] c2 = new double[dim];
        for (int i = 0; i < dim; i++) {
            double v = 0.5 * ((1 + cf[i]) * p1[i] + (1 - cf[i]) * p2[i]);
            if (v > 1)
                v = 1;
            else if (v < 0)
                v = 0;
            c1[i] = v;

            double v2 = 0.5 * ((1 - cf[i]) * p1[i] + (1 + cf[i]) * p2[i]);
            if (v2 > 1)
                v2 = 1;
            else if (v2 < 0)
                v2 = 0;
            c2[i] = v2;
        }

        ArrayList<double[]> offsp = new ArrayList<double[]>();
        offsp.add(c1);
        offsp.add(c2);

        return offsp;
    }

}