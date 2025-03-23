package Tests;


import Neural_Network.Layer;
import Neural_Network.Network;
import Neural_Network.Neuron;

import java.util.*;
public class Test {

    public static void main(String[] args) {
        Network n = new Network(new int[]{2, 2});

        n.setLearn_rate(0.7);

        n.layers[0].neurons[0].weights = new double[]{0.5, 0.2};
        n.layers[0].neurons[1].weights = new double[]{0.5, 0.2};
        n.layers[1].neurons[0].bias = 1.83;
        n.layers[1].neurons[1].bias = 1.83;

        n.feedNetwork(new double[]{0.1, 0.3});
        printNeurons(n);




        /*
        Network n = new Network(new int[]{2, 2, 1});

        n.setLearn_rate(0.7);

        for(Neuron ne : n.layers[0].neurons){
            ne.weights = new double[]{0.2, 0.3};
            ne.bias = 0;
        }
        for(Neuron ne : n.layers[1].neurons){
            ne.bias = 0;
        }
        for(Neuron ne : n.layers[2].neurons){
            ne.bias = 0;
        }

        n.layers[1].neurons[0].weights = new double[]{0.3};
        n.layers[1].neurons[1].weights = new double[]{0.9};


        printNeurons(n);

        double[] input = new double[]{0.35, 0.7};
        n.test(input);
        printNeurons(n);
        for(int i = 0;i<10;i++){
            n.learn(input, new double[]{0.5});
            printNeurons(n);

        }
        //System.out.println(n.layers[2].neurons[0].delta);

        n.test(input);
        printNeurons(n);

     */

        //n.test(new double[]{2.0, 1.0});
        /*
        for(Layer l : n.layers){
            //System.out.print(l.neuron_number+"-"+l.neuron_next_number+"  ");

            for(int i = 0; i<l.neuron_number;i++){
                System.out.println(l.neurons[i].neuron_value+" - "+l.neurons_next[i]+"  ");
            }
            System.out.println(" ");
        }

         */

        /*
        Game.Board b = new Game.Board();
        b.makeMove(1);
        b.makeMove(1);
        b.makeMove(1);
        b.makeMove(1);
        b.makeMove(1);
        b.makeMove(1);

        b.makeMove(2);
        b.makeMove(2);
        b.makeMove(3);
        b.makeMove(2);
        b.makeMove(2);
        b.makeMove(2);
        b.makeMove(2);
        //b.makeMove(2);

        //b.makeMove(1);
        b.printBoard();
        */

    }
    public static void printNeurons(Network n){
        for(Layer l : n.layers) {
            for (Neuron ne : l.neurons) {
                System.out.print(ne.neuron_value + " d:("+ne.delta+")"+" w:(");
                for(double d : ne.weights){
                    System.out.print(d+"-");
                }
                System.out.print(")  ");
            }
            System.out.println("");
        }
        System.out.println("");


    }


}