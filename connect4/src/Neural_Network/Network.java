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

    Function activationFunction = Functions.ReLUFunction;
    Derivative activationDerivative = Derivatives.ReLUDerivative;
    Neural_Network.Functions.Cost.Function costFunction = Neural_Network.Functions.Cost.Functions.cceFunction;
    Neural_Network.Functions.Cost.Derivative costDerivative = Neural_Network.Functions.Cost.Derivatives.ccaDerivative;

    public double cost_of_previous_feed;
    public Network(int[] neurons_numbers){
        this.layer_number = neurons_numbers.length;
        this.input_neurons_number = neurons_numbers[0];
        this.hidden_layers_number = layer_number-2;
        this.output_neurons_number = neurons_numbers[layer_number-1];
        this.neurons_numbers = neurons_numbers;

        layers = new Layer[2+hidden_layers_number];
        makeLayers();

        input_layer = layers[0];
        output_layer = layers[layers.length-1];

        output_layer.last_layer = true;

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

        layers[0] = new Layer(new Neuron[input_neurons_number], neurons_numbers[1]);
        layers[0].activationFunction = activationFunction;
        layers[0].activationDerivative = activationDerivative;
        for (int i = 0; i< hidden_layers_number; i++){
            layers[i+1] = new Layer(layers[i].neurons_next, neurons_numbers[i+2]);
            layers[i+1].activationFunction = activationFunction;
            layers[i+1].activationDerivative = activationDerivative;
        }
        layers[layer_number-1] = new Layer(layers[layer_number-2].neurons_next, layers[layer_number-2].neurons_next.length); //set up output layer --> next neurons for correct values
        layers[layer_number-1].activationFunction = Functions.softMaxFunction;
        layers[layer_number-1].activationDerivative = Derivatives.softMaxDerivative;
        layers[layer_number-1].costFunction = costFunction;
        layers[layer_number-1].costDerivative = costDerivative;
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
        for(Layer layer:layers){
            layer.calculateDeltas();
            if(layer!=output_layer)layer.calculateWeightsBiases(learn_rate);
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

