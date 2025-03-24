package Neural_Network;

import Neural_Network.Functions.Activation.Derivative;
import Neural_Network.Functions.Activation.Function;

import java.io.Serializable;

public class Layer implements Serializable{
    public boolean last_layer = false;
    public int neuron_number; //how many neurons are in this layer
    public int neuron_next_number; //how many neurons are in next layer if there are 0 it means it is output layer

    public Function activationFunction;
    public Derivative activationDerivative;

    public Neural_Network.Functions.Cost.Function costFunction;
    public Neural_Network.Functions.Cost.Derivative costDerivative;

    public Neuron[] neurons;
    public Neuron[] neurons_next;


    public Layer(Neuron[] neurons, int neuron_next_number){
        this.neuron_number = neurons.length;
        this.neurons = neurons;

        this.neuron_next_number = neuron_next_number;
        neurons_next = new Neuron[neuron_next_number];

        setUpNeurons();
        setUpNextNeurons();
    }


    public void calculateNextNeurons(){
        for (int i = 0; i < neuron_next_number; i++){
            double neuron_next_value = 0;

            //String a = "";
            for (int j = 0; j < neuron_number; j++){

                //System.out.println(j);
                //a += neurons[j].neuron_value+"*"+neurons[j].weights[i]+"+";
                neuron_next_value += neurons[j].neuron_value_a*neurons[j].weights[i];
                //System.out.println(neuron_next_value);
            }
            neuron_next_value += neurons_next[i].bias;

            neurons_next[i].setNeuron_value(neuron_next_value, activationFunction.execute(neuron_next_value));

        }
    }
    public void calculateDeltas(){
        if(last_layer){
            for (int i = 0;i < neuron_number;i++) {
                double da = activationDerivative.execute(getNeuron_values_a())[i];//output neuron on activationFunction
                double dc = costDerivative.execute(neurons[i].neuron_value_a, neurons_next[i].neuron_value);//activationFunction on cost
                neurons[i].delta = da * dc;
            }
        }else {
            for (int i = 0;i < neuron_number;i++) {
                double da = activationDerivative.execute(getNeuron_values_a())[i];// neuron on activationFunction
                neurons[i].delta = 0;
                for (int k = 0; k < neurons_next.length; k++) {
                    neurons[i].delta += da * neurons[i].weights[k] * neurons_next[k].delta;
                }
            }
        }
    }
    public void calculateWeightsBiases(double learn_rate){
        for (Neuron neuron : neurons) {
            for (int i = 0; i < neurons_next.length; i++) {
                neuron.weights[i] -= learn_rate * (neurons_next[i].delta * neuron.neuron_value_a);
                neuron.bias -= learn_rate * neurons_next[i].delta;
            }
        }
    }
    public double calculateCost(double[] expected_values){
        double cost = 0.0;
        if(!last_layer) return cost;

        return costFunction.execute(getNeuron_values_a(), expected_values);
    }

    public void setUpNeurons(){
        for(int i = 0; i<neuron_number;i++){
            neurons[i] = new Neuron(neuron_next_number);
        }
    }
    public void setUpNextNeurons(){
        for(int i = 0; i<neuron_next_number;i++){
            neurons_next[i] = new Neuron(0);
        }
    }

    public void setNeurons(double[] neuron_values){
        for(int i = 0; i<neuron_number;i++){
            //sets unactivated and activated neuron values
            neurons[i].setNeuron_value(neuron_values[i], activationFunction.execute(neuron_values[i]));

        }
    }
    public void setNextNeurons(double[] neuron_next_values){
        //for setting correct output
        for(int i = 0; i<neuron_next_number;i++){
            neurons_next[i].setNeuron_value(neuron_next_values[i], activationFunction.execute(neuron_next_values[i]));
        }
    }

    public double[] getNeuron_values_a(){
        double[] neuron_values_a=new double[neuron_number];
        for(int i = 0; i<neuron_number;i++){
            neuron_values_a[i] = neurons[i].neuron_value_a;
        }
        return neuron_values_a;
    }
}
