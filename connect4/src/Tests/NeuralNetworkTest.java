package Tests;

import Neural_Network.Functions.Activation.Functions;
import Neural_Network.Layer;
import Neural_Network.Network;
import Neural_Network.Neuron;

import java.util.Arrays;
import java.util.Random;

public class NeuralNetworkTest {

    private static final Random rand = new Random();
    private static Neural_Network.Functions.Functions functions = new Neural_Network.Functions.Functions(Functions.tanHFunction, Functions.softMaxFunction, Neural_Network.Functions.Cost.Functions.cceFunction);
    public static Network n = new Network(new int[]{2, 4, 4, 2}, functions);


    public static void main(String[] args) {

        n.setLearn_rate(0.1);

        double[][][] data_set = createDataSets(100);//dataset with 100 examples
        /*
        learnOnOneExample(5, 6, 100);
        learnOnOneExample(5, 1, 100);
        System.out.println();
        testOnOneExample(5, 6);*/

        learn(1000, data_set);
        System.out.println();
        test(10, data_set);

    }

    public static double[][][] createDataSets(int num){
        double min = -10.0;
        double max = 10.0;

        double[][][] output = new double[2][num][2];
        for (int i = 0; i < num; i++) {
            double x = getRanDouble(min, max);
            double y = getRanDouble(min, max);

            output[0][i] = new double[]{x, y};
            //System.out.println(x+" "+y);
            output[1][i] = fx(x, y);
        }
        //returns: [[[x1,y1], [x2,y2]], [[1,0], [0,1]]]
        return output;
    }


    public static void learn(int number_of_epoch, double[][][] data_set){
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

            double cost = n.learnNetwork(data_set[0], data_set[1]);
            if(i%50==0) {
                System.out.println(cost);
            }
        }

    }
    public static void test(int number_of_tests, double[][][] data_set){

        for (int i = 0; i < number_of_tests; i++) {

            int rand_d = getRanInt(0, data_set[0].length);
            //System.out.println(x+" "+ y);

            double x = data_set[0][rand_d][0];
            double y = data_set[0][rand_d][1];

            System.out.println(x+" "+y+" "+(data_set[1][rand_d][0]==1?"true":"false")+" "+Arrays.toString(n.feedNetwork(new double[]{x, y}))+" "+n.calculateCost(data_set[1][rand_d]));

        }
    }
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

    public static double[] evenOdd(double n){
        return n%2==0?new double[]{1, 0}:new double[]{0, 1};
    }

    public static double[] fx(double x, double y){
        boolean f = (2*x-5)<=y && y<=(2*x+5);
        return f?new double[]{1, 0}:new double[]{0, 1};
    }

    public static double getRanDouble(double min, double max){
        double num = min + ((max - min) * rand.nextDouble());
        return num;
    }

    public static int getRanInt(int min, int max){
        return min + (int)(Math.random() * ((max - min)));
    }

}
