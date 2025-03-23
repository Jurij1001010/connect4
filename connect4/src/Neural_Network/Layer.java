package Neural_Network;
import java.io.Serializable;
import java.util.Arrays;

public class Layer extends Functions implements Serializable{
    public int position; // 0 = input and hidden layer, 1 = output layer
    public int neuron_number; //how many neurons are in this layer
    public int neuron_next_number; //how many neurons are in next layer if there are 0 it means it is output layer

    public Neuron[] neurons;
    public Neuron[] neurons_next;


    public Layer(Neuron[] neurons, int neuron_next_number){
        this.neuron_number = neurons.length;
        this.neurons = neurons;

        this.neuron_next_number = neuron_next_number;
        neurons_next = new Neuron[neuron_next_number];

        if (neuron_next_number == 0){
            this.position = 1;
        }else {
            this.position = 0;
            setNeurons();
            setNeuronsNext();
        }
    }

    public void setNeurons(){
        for(int i = 0; i<neuron_number;i++){
            neurons[i] = new Neuron(neuron_next_number);
        }
    }
    public void setNeuronsNext(){
        for(int i = 0; i<neuron_next_number;i++){
            neurons_next[i] = new Neuron(0);
        }
    }
    public void setNeurons(double[] neuron_values){
        for(int i = 0; i<neuron_number;i++){
            neurons[i].setNeuron_value(neuron_values[i]);
        }
    }


    public void calculateNextNeurons(){
        //if is last layer dont go in this loop
        for (int i = 0; i < neuron_next_number; i++){
            Neuron next_neuron = neurons_next[i];
            next_neuron.neuron_value = 0;

            for (int j = 0; j < neuron_number; j++){
                //System.out.println(j);
                next_neuron.neuron_value += neurons[j].neuron_value*neurons[j].weights[i];
                //System.out.println(next_neuron);
            }
            next_neuron.neuron_value += neurons_next[i].bias;
            next_neuron.neuron_value = sigmoidFunction(next_neuron.neuron_value);
        }
    }


    public void setWeights(double[] weights){
        for(int i = 0;i<neuron_number;i++){
            neurons[i].delta = weights[i];

        }
    }
    public void setBiases(double[] biases){
        for(int i = 0;i<neuron_number;i++){
            neurons[i].delta = biases[i];
        }
    }
    public void setNeuronDeltas(double[] neuron_deltas){
        for(int i = 0;i<neuron_number;i++){
            neurons[i].delta = neuron_deltas[i];
        }
    }




    public double[] getCost(double[] correct_neuron_values){
        double[] costs = new double[neuron_number];

        for (int i = 0; i < neuron_number; i++){
            double neuron_value = neurons[i].neuron_value;
            double correct_neuron_value = correct_neuron_values[i];

            costs[i] = (correct_neuron_value-neuron_value)*(neuron_value*(1-neuron_value));
            //Math.pow(correct_neuron_value-neuron_value, 2)/(neuronValues.length-1));
        }
        System.out.println("costs: "+ Arrays.toString(costs));
        return costs;
    }
    public double[] getCostHiddenLayer(){
        double[] costs = new double[neuron_number];

        for(int i = 0; i < neuron_number; i++){

            double a = 0;
            for(int j = 0; j < neuron_next_number; j++){
                a += neurons[i].weights[j]*neurons_next[j].delta;
            }
            costs[i] = a*(neurons[i].neuron_value *(1- neurons[i].neuron_value));
        }

        System.out.println("costs: "+ Arrays.toString(costs));
        return costs;
    }

    public double[] getNeuron_values(){
        double[] neuron_values=new double[neuron_number];
        for(int i = 0; i<neuron_number;i++){
            neuron_values[i] = neurons[i].neuron_value;
        }
        return neuron_values;
    }
    public double[] getNeuron_deltas(){
        double[] neuron_deltas=new double[neuron_number];
        for(int i = 0; i<neuron_number;i++){
            neuron_deltas[i] = neurons[i].delta;
        }
        return neuron_deltas;
    }
}
