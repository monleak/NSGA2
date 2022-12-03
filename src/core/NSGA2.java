package core;

import basic.Individual;
import basic.Params;
import basic.Population;
import benchmark.Problem;
import util.SBX;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static util.IGD.EuclideanDistance;

public class NSGA2 {
    public Population pop;
    public Problem prob;


    public NSGA2(Problem prob) {
        this.prob = prob;
        this.pop = new Population(prob);
    }

    public double run1(){
        Params.countEvals=0;
        int countGEN = 0;

        this.pop.init(prob.countObj);
        this.pop.update();

        for(int i=0;i<pop.Front.get(0).size();i++){
            System.out.print(countGEN +"||"+i+":");
            for(int j=0;j< pop.Front.get(0).get(i).dim;j++) {
                System.out.print(pop.Front.get(0).get(i).chromosome[j]+" ");
            }
            System.out.print("\n");
        }

        while (countGEN <= 300) {
            ArrayList<Individual> offspring = new ArrayList<Individual>();
            for (Individual indiv : pop.pop) {
                Individual indiv2;
                do{
                    indiv2 = pop.pop.get(Params.rand.nextInt(pop.pop.size()));
                }while (indiv2.individual_id == indiv.individual_id);

                ArrayList<Individual> child = inter_crossover(indiv,indiv2);
                for(int i=0;i<child.size();i++){
                    prob.calFitness(child.get(i));
                }
                offspring.addAll(child);
            }
            pop.pop.addAll(offspring);
            pop.update();
            countGEN++;

//            for(int i=0;i<pop.Front.get(0).size();i++){
//                System.out.print(countGEN +"||"+i+":"+pop.Front.get(0).get(i).fitness[0]+" "+pop.Front.get(0).get(i).fitness[1]+"\n");
//            }
            for(int i=0;i<pop.Front.get(0).size();i++){
                System.out.print(countGEN +"||"+i+":");
                for(int j=0;j< pop.Front.get(0).get(i).dim;j++) {
                    System.out.print(pop.Front.get(0).get(i).chromosome[j]+" ");
                }
                System.out.print("\n");
            }
        }
        double IGD = 0.0;
        for(int i=0;i<pop.Front.get(0).size();i++){
            /* ZDT1
            double[] temp = new double[pop.Front.get(0).get(i).chromosome.length];
            temp[0] = pop.Front.get(0).get(i).chromosome[0];
            */

            //DTLZ1
            double[] temp = new double[pop.Front.get(0).get(i).chromosome.length];
            Arrays.fill(temp,0.5);
            temp[0] = pop.Front.get(0).get(i).chromosome[0];
            temp[1] = pop.Front.get(0).get(i).chromosome[1];

            IGD += Math.pow(EuclideanDistance(pop.Front.get(0).get(i).chromosome,temp),2);
        }
        IGD = Math.sqrt(IGD)/pop.Front.get(0).size();

        return IGD;
    }
    private ArrayList<Individual> inter_crossover(Individual p1, Individual p2) {
        ArrayList<double[]> offspring_gene = SBX.generateOffspring(p1.chromosome, p2.chromosome);
        for (int i=0;i< prob.n;i++){
            if(Params.rand.nextDouble()<0.5){
                double temp = offspring_gene.get(0)[i];
                offspring_gene.get(0)[i] = offspring_gene.get(1)[i];
                offspring_gene.get(1)[i] = temp;
            }
        }
        ArrayList<Individual> offspring = new ArrayList<Individual>();
        offspring.add(new Individual(offspring_gene.get(0), prob.countObj));
        offspring.add(new Individual(offspring_gene.get(1), prob.countObj));
        return offspring;
    }
}
