package basic;

import benchmark.Problem;

import java.util.ArrayList;

public class Population {
    public ArrayList<Individual> pop;
    public Problem prob;
    public ArrayList<ArrayList<Individual>> Front;

    public Population(){
        this.pop = new ArrayList<>();
        this.Front = new ArrayList<>();
    }
    public void init(){
        while (pop.size() < Params.maxSizePOP){
            Individual indiv = new Individual(prob.n);
            indiv.init();
            pop.add(indiv);
        }
        for(int i=0;i<pop.size();i++){
            pop.get(i).fitness[0] = prob.calF1(pop.get(i).chromosome);
            pop.get(i).fitness[1] = prob.calF2(pop.get(i).chromosome);
        }
    }
    public void update(){
        for(int i=0;i<pop.size();i++){
            pop.get(i).S.clear();
            pop.get(i).countDomination = 0;
            for(int j=0;j<pop.size();j++){
                if(pop.get(i).individual_id == pop.get(j).individual_id){
                    continue;
                }
                if(isDominates(pop.get(i),pop.get(j))){
                    pop.get(i).S.add(pop.get(j));
                }else if(isDominates(pop.get(j),pop.get(i))){
                    pop.get(i).countDomination++;
                }
            }
            if(pop.get(i).countDomination == 0){
                pop.get(i).rank = 0;
                if(Front.size() < 1){
                    ArrayList<Individual> F1 = new ArrayList<>();
                    Front.add(F1);
                }
                Front.get(0).add(pop.get(i));
            }
        }

        int count = 0;
        if(Front.size() < count+1){
            ArrayList<Individual> F = new ArrayList<>();
            Front.add(F);
        }
        while (Front.get(count).size() != 0){
            ArrayList<Individual> Q = new ArrayList<>();
            for (int i=0;i<Front.get(count).size();i++){
                for(int j=0;j<Front.get(count).get(i).S.size();j++){
                    Front.get(count).get(i).S.get(j).countDomination--;
                    if(Front.get(count).get(i).S.get(j).countDomination == 0){
                        Front.get(count).get(i).S.get(j).rank = count+1;
                        Q.add(Front.get(count).get(i).S.get(j));
                    }
                }
            }
            count++;
            if(Front.size() < count+1){
                ArrayList<Individual> F = new ArrayList<>();
                Front.add(F);
            }
            Front.get(count).clear();
            Front.get(count).addAll(Q);
        }
    }

    public boolean isDominates(Individual x1, Individual x2){
        if( (x1.fitness[0] < x2.fitness[0] && x1.fitness[1] <= x2.fitness[1])
            || (x1.fitness[0] <= x2.fitness[0] && x1.fitness[1] < x2.fitness[1])
        ){
            return true;
        }else {
            return false;
        }
    }
}
