package Tests;

import Neural_Network.Layer;
import Neural_Network.Network;
import Neural_Network.Neuron;

import java.util.Arrays;
import java.util.Random;

public class NeuralNetworkTest {

    private final Random rand = new Random();
    public static Network n = new Network(new int[]{2, 3, 3,  2});

    public static void main(String[] args) {

        n.setLearn_rate(0.3);

        learn(10000);
        test(100);
    }

    public static void learn(int number_of_learns){


        //define learn grid
        double min = -10;
        double max = 10;

        for (int i = 0; i < number_of_learns; i++) {
            double x = getRanInt(min, max);
            double y = getRanInt(min, max);

            boolean onFx = fx(x, y);
            n.learnNetwork(new double[]{x, y}, onFx?new double[]{1, 0}:new double[]{0, 1});

        }

    }
    public static void test(int number_of_tests){

        //define learn grid
        double min = -10;
        double max = 10;

        //count[0] on graph, count[1] not on graph
        int[] count = new int[]{0, 0};

        int correct_true = 0;
        int correct_false = 0;


        for (int i = 0; i < number_of_tests; i++) {

            double x = getRanInt(min, max);
            double y = getRanInt(min, max);

            boolean onFx = fx(x, y);
            n.feedNetwork(new double[]{x, y});
            boolean network_prediction = n.output_layer.getNeuron_values_output()[0]>n.output_layer.getNeuron_values_output()[1];

            if(onFx){
                count[0] ++;
            }else{
                count[1] ++;
            }

            if(onFx == network_prediction) {
                if(onFx) correct_true += 1;
                if(!onFx) correct_false +=1;

                System.out.println(i);
                System.out.println("x: " + x + " y: " + y);
                System.out.println("point on graph: " + onFx);

                System.out.println("network output: " + (network_prediction ? "true" : "false") + "  " + Arrays.toString(n.output_layer.getNeuron_values_output()));
                System.out.println();
            }

        }
        double score = (double) (correct_false + correct_true) /number_of_tests *100;
        System.out.println("true correct: "+((double)(correct_true/count[0])*100)+" false correct: "+((double)(correct_false/count[1])*100)+ " score: "+score);


    }

    public static boolean fx(double x, double y){

        return (2*x)<=y && y<=(2*x+2);
    }

    public static double getRanDouble(double min, double max){
        return min + (Math.random() * ((max - min)));
    }
    public static double getRanInt(double min, double max){
        return min + (int)(Math.random() * ((max - min)));
    }


    public static void printNeurons(Network n){
        for(Layer l : n.layers) {
            for (Neuron ne : l.neurons) {
                System.out.print(ne.neuron_value_output +"  ");
                /*
                System.out.print(ne.neuron_value + " d:("+ne.delta+")"+" w:(");
                for(double d : ne.weights){
                    System.out.print(d+"-");
                }
                System.out.print(")  ");

                 */
            }
            System.out.println("");
        }
        System.out.println("cost: "+n.cost_of_previous_feed);
        System.out.println("");


    }

}
