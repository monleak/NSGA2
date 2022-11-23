package core;

import basic.Individual;
import basic.Params;
import basic.Population;
import benchmark.Problem;
import util.SBX;

import java.util.ArrayList;

public class NSGA2 {
    public Population pop;
    public Problem prob;

    public NSGA2(Problem prob) {
        this.prob = prob;
        this.pop = new Population(prob);

    }

    public void run1(){
        Params.countEvals=0;
        int countGEN = 0;

        this.pop.init();
        this.pop.update();

        for(int i=0;i<pop.Front.get(0).size();i++){
            System.out.print(countGEN +"||"+i+":");
            for(int j=0;j< pop.Front.get(0).get(i).dim;j++) {
                System.out.print(pop.Front.get(0).get(i).chromosome[j]+" ");
            }
            System.out.print("\n");
        }

        while (countGEN <= 500) {
            ArrayList<Individual> offspring = new ArrayList<Individual>();
            for (Individual indiv : pop.pop) {
                Individual indiv2;
                do{
                    indiv2 = pop.pop.get(Params.rand.nextInt(pop.pop.size()));
                }while (indiv2.individual_id == indiv.individual_id);

                ArrayList<Individual> child = inter_crossover(indiv,indiv2);
                for(int i=0;i<child.size();i++){
                    child.get(i).fitness[0] = prob.calF1(child.get(i).chromosome);
                    child.get(i).fitness[1] = prob.calF2(child.get(i).chromosome);
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
        offspring.add(new Individual(offspring_gene.get(0)));
        offspring.add(new Individual(offspring_gene.get(1)));
        return offspring;
    }
}
