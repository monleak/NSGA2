package core;

import basic.Population;
import benchmark.Problem;

public class NSGA2 {
    public Population pop;
    public Problem prob;

    public NSGA2(Problem prob) {
        this.prob = prob;
        this.pop = new Population(prob);
        this.pop.init();
        this.pop.update();
    }

    public void run1(){

    }
}
