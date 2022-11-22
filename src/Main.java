import basic.Params;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        for(int seed = 0;seed < Params.REPT;seed++){
            Params.rand = new Random(seed);
        }
    }
}