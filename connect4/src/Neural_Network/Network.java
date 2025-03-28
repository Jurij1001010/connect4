package Neural_Network;

import Neural_Network.Functions.Activation.Derivative;
import Neural_Network.Functions.Activation.Derivatives;
import Neural_Network.Functions.Activation.Function;
import Neural_Network.Functions.Activation.Functions;

import java.io.Serializable;

public class Network implements Cloneable, Serializable {
    public Layer input_layer;
    public Layer output_layer;

    public final int input_neurons_number; //how many neurons are in input layer
    public final int output_neurons_number; //how many neurons are in output layer
    public final int hidden_layers_number; //how many hidden layers are
    public int layer_number;//how many layers
    public int[] neurons_numbers;

    public Layer[] layers;
    private double learn_rate = 0.1;

    Neural_Network.Functions.Functions functions;

    public double cost_of_previous_feed;

    public Network(int[] neurons_numbers){
        this.layer_number = neurons_numbers.length;
        this.input_neurons_number = neurons_numbers[0];
        this.hidden_layers_number = layer_number-2;
        this.output_neurons_number = neurons_numbers[layer_number-1];
        this.neurons_numbers = neurons_numbers;

        functions = new Neural_Network.Functions.Functions(Functions.sigmoidFunction, Neural_Network.Functions.Cost.Functions.cceFunction);
        functions.setActivationO(Functions.softMaxFunction);

        layers = new Layer[2+hidden_layers_number];
        makeLayers();
    }

    public void resetLayers(){
        for(Layer l : layers){
            for(Neuron n : l.neurons){
                n.setNewWeights();
                n.setNewBias();
            }
        }
    }
    public void setLayers(Network n){
        for(int i = 0; i<layers.length;i++){
            layers[i].neurons = n.layers[i].neurons.clone();
        }
    }
    public void makeLayers(){
        //if there is 0 hidden layers we only need to set up input and output layers

        layers[0] = new Layer(new Neuron[input_neurons_number], neurons_numbers[1], functions);
        for (int i = 0; i< hidden_layers_number; i++){
            layers[i+1] = new Layer(layers[i].neurons_next, neurons_numbers[i+2], functions);
        }
        layers[layer_number-1] = new Layer(layers[layer_number-2].neurons_next, 0, functions); //set up output layer

        input_layer = layers[0];
        output_layer = layers[layers.length-1];
    }

    public double[] feedNetwork(double[] input_neuron_values){
        input_layer.setNeurons(input_neuron_values);

        for (Layer layer : layers) {
            if(layer==output_layer)break;
            layer.calculateNextNeurons();
        }
        return output_layer.getNeuron_values_a();
    }


    public void learnNetwork(double[] input_neuron_values, double[] correct_neuron_values){

        //forward-propagation
        feedNetwork(input_neuron_values);

        output_layer.setNextNeurons(correct_neuron_values);//set last neurons as correct values
        cost_of_previous_feed = output_layer.calculateCost(correct_neuron_values);
        //back-propagation
        for (int i = layer_number-1; i >= 0; i--){
            layers[i].calculateDeltas();
            if(layers[i]!=output_layer)layers[i].calculateWeightsBiases(learn_rate);
        }
    }

    public void setLearn_rate(double learn_rate) {
        this.learn_rate = learn_rate;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

