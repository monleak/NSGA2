package basic;

import java.util.Random;

public class Params {
    public static Random rand; //Được khởi tạo theo các seed
    public static int countEvals; //Đếm số lần đánh giá
    public static int maxEvals; //Số lần đánh giá tối đa

    public static int maxSizeChromosome = 30; //Số chiều của không gian tìm kiếm
    public static int REPT = 30;
    public static int maxSizePOP = 100;
}
