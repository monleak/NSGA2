package basic;

import java.util.ArrayList;

public class Individual implements Comparable<Individual>{
    public static int counter = 0; //Đếm id của cá thể
    public int individual_id;
    public int dim;
    public double[] chromosome;
    public double[] fitness;
    public int rank;

    public ArrayList<Individual> S; //solutions dominated by this
    public int countDomination;
    public Individual(int dim) {
        this.individual_id = Individual.counter++;
        this.dim = dim;
        this.chromosome = new double[this.dim];
        this.S = new ArrayList<>();
        this.rank = Integer.MAX_VALUE;
    }
    public void init(){
        for(int i=0;i<dim;i++){
            chromosome[i] = Params.rand.nextDouble();
        }
    }
    @Override
    public int compareTo(Individual o) {
        return Double.valueOf(this.fitness[0]).compareTo(o.fitness[0]);
    }
}
