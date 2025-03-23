package Neural_Network;
import java.io.Serializable;
import java.util.Arrays;

public class Layer extends Functions implements Serializable{
    public int neuron_number; //how many neurons are in this layer
    public int neuron_next_number; //how many neurons are in next layer if there are 0 it means it is output layer

    public Neuron[] neurons;
    public Neuron[] neurons_next;


    public Layer(Neuron[] neurons, int neuron_next_number){
        this.neuron_number = neurons.length;
        this.neurons = neurons;

        this.neuron_next_number = neuron_next_number;
        neurons_next = new Neuron[neuron_next_number];

        setUpNeurons();
        setUpNeuronsNext();
    }


    public void setUpNeurons(){
        for(int i = 0; i<neuron_number;i++){
            neurons[i] = new Neuron(neuron_next_number);
        }
    }
    public void setUpNeuronsNext(){
        for(int i = 0; i<neuron_next_number;i++){
            neurons_next[i] = new Neuron(0);
        }
    }

    public void setNeurons(double[] neuron_values){
        for(int i = 0; i<neuron_number;i++){
            neurons[i].setNeuron_value(neuron_values[i]);
        }
    }
    public void setNextNeurons(double[] neuron_next_values){
        //for setting correct output
        for(int i = 0; i<neuron_next_number;i++){
            neurons_next[i].setNeuron_value(neuron_next_values[i]);
        }
    }


    public void calculateNextNeurons(){
        for (int i = 0; i < neuron_next_number; i++){
            double neuron_next_value = 0;

            //String a = "";
            for (int j = 0; j < neuron_number; j++){
                //System.out.println(j);
                //a += neurons[j].neuron_value+"*"+neurons[j].weights[i]+"+";
                neuron_next_value += neurons[j].neuron_value*neurons[j].weights[i];
                //System.out.println(neuron_next_value);
            }
            neuron_next_value += neurons_next[i].bias;

            neurons_next[i].setNeuron_value(neuron_next_value);
            /*
            System.out.println(a+""+neurons_next[i].bias);
            System.out.println("neuron value: "+neuron_next_value.neuron_value);

             */
        }
    }
    public void passNeuronsTroughReLUFunction(){
        for(Neuron n : neurons){
            n.neuron_value = ReLUFunction(n.neuron_value);
        }
    }
    public void passNeuronsTroughSoftMaxFunction(){
        double[] new_values = softMaxFunction(getNeuron_values());
        for(int i = 0; i<neuron_number;i++){
            neurons[i].neuron_value = new_values[i];
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






    public double[] getNeuron_values(){
        double[] neuron_values=new double[neuron_number];
        for(int i = 0; i<neuron_number;i++){
            neuron_values[i] = neurons[i].neuron_value;
        }
        return neuron_values;
    }
    public double[] getNeuron_next_values(){
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
