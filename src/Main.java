import basic.Params;
import benchmark.DTLZ1;
import benchmark.ZDT1;
import core.NSGA2;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        ZDT1 prob = new ZDT1();
        DTLZ1 prob = new DTLZ1();
//        for(int seed = 0;seed < Params.REPT;seed++){
            Params.rand = new Random(0);
            NSGA2 solver = new NSGA2(prob);
            double IGD = solver.run1();
            System.out.print(IGD);
//        }
    }
}