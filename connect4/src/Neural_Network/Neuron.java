package Neural_Network;

import java.util.Random;

public class Neuron extends Functions{


    public double neuron_value; // value of neuron
    public double neuron_value_a;
    public int neuron_next_number; // how many weights
    public double[] weights;
    public double bias;

    public double delta;


    private final Random rand = new Random();
    private int weight_limit = 2;
    private int bias_limit = 1;

    public Neuron(int neuron_next_number) {
        this.neuron_next_number = neuron_next_number;
        weights = new double[neuron_next_number];
        if(neuron_next_number!=0) {
            setNewWeights();
            setNewBias();
        }
    }


    public void setNewWeights(){
        for (int i = 0; i < neuron_next_number; i++){
            double randomNum = -weight_limit + (weight_limit + weight_limit) * rand.nextDouble();
            weights[i] = randomNum;
        }
    }

    public void setNewBias(){
        bias = -bias_limit + (bias_limit + bias_limit) * rand.nextDouble();
    }

    public void setNeuron_value(double neuron_value) {
        this.neuron_value = neuron_value;
        this.neuron_value_a = neuron_value;
    }
}
