package Tests;

import Neural_Network.Functions.Activation.Functions;
import Neural_Network.Network;

import java.util.Arrays;
import java.util.Random;

public class NeuralNetworkTest {

    private static final Random rand = new Random();
    private static Neural_Network.Functions.Functions functions = new Neural_Network.Functions.Functions(Functions.tanHFunction, Functions.softMaxFunction, Neural_Network.Functions.Cost.Functions.cceFunction);
    public static Network n = new Network(new int[]{2,4,4,2}, functions);


    public static void main(String[] args) {

        n.setLearn_rate(0.01);

        DataSet data_set = new DataSet(100, 2, 2, 10);

        /*
        learnOnOneExample(5, 6, 100);
        learnOnOneExample(5, 1, 100);
        System.out.println();
        testOnOneExample(5, 6);*/
        test(10, data_set);
        System.out.println();
        learn(10000, data_set);
        System.out.println();
        test(10, data_set);

    }




    public static void learn(int number_of_epoch, DataSet data_set){
        //define learn grid

        for (int i = 0; i < number_of_epoch; i++) {
            /*
            double x = data_set[j][0];
            double y = data_set[j][1];
            */
            //double[] onFx = fx(x, y);
            //System.out.println(Arrays.toString(onFx)+" "+(onFx[0]==1?"true":"false"));
            //System.out.println(x+" "+ Arrays.toString(onFx));
            //System.out.println("learning: "+x+" "+y+"  means: "+ Arrays.toString(onFx));
            data_set.setRandomBatch();
            double cost = n.learnNetwork(data_set.batch_data, data_set.batch_results);
            if(i%100==0) {
                System.out.println(i+" "+cost);
            }
        }

    }
    public static void test(int number_of_tests, DataSet data_set){

        for (int i = 0; i < number_of_tests; i++) {
            double[][] random_data = data_set.getRandomData();
            double[] data = random_data[0];
            double[] results = random_data[1];


            System.out.println(data[0]+" "+data[1]+" "+(results[0]==1?"true":"false")+" "+Arrays.toString(n.feedNetwork(data))+" "+n.calculateCost(results));

        }
    }
    /*
    public static void learnOnOneExample(int x, int y, int num){
        double[] onFx = fx(x, y);
        double[] output;
        double cost = 0;
        System.out.println(x+" "+y+" "+(onFx[0]==1?"true":"false")+" "+Arrays.toString(n.feedNetwork(new double[]{x, y}))+" "+n.calculateCost(onFx));
        double start_cost = n.calculateCost(onFx);
        for (int i = 0; i < num; i++) {
            n.learnNetwork(new double[]{x, y}, onFx);
            output = n.output_layer.getNeuron_values_output();
            cost = n.calculateCost(onFx);
            if(cost>start_cost){
                System.out.println("aaaa");
                System.out.println(x+" "+y+" "+(onFx[0]==1?"true":"false")+" "+Arrays.toString(n.feedNetwork(new double[]{x, y}))+" "+n.calculateCost(onFx));
                System.out.println();
                n.setLearn_rate(0.7);
            }else {
                n.setLearn_rate(0.3);
            }


        }
        System.out.println(x+" "+y+" "+(onFx[0]==1?"true":"false")+" "+Arrays.toString(n.feedNetwork(new double[]{x, y})) +" "+cost);
    }


    public static void testOnOneExample(int x, int y){
        double[] onFx = fx(x, y);
        System.out.println(x+" "+y+" "+(onFx[0]==1?"true":"false")+" "+Arrays.toString(n.feedNetwork(new double[]{x, y}))+" "+n.calculateCost(onFx));
    }

     */

    public static double[] evenOdd(double n){
        return n%2==0?new double[]{1, 0}:new double[]{0, 1};
    }


    public static double getRanDouble(double min, double max){
        double num = min + ((max - min) * rand.nextDouble());
        return num;
    }

    public static int getRanInt(int min, int max){
        return min + (int)(Math.random() * ((max - min)));
    }

}
