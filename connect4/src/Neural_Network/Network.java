package Neural_Network;

import java.io.Serializable;

public class Network extends Functions implements Cloneable, Serializable {
    public Layer input_layer;
    public Layer output_layer;


    public final int input_neurons_number; //how many neurons are in input layer
    public final int output_neurons_number; //how many neurons are in output layer
    public final int hidden_layers_number; //how many hidden layers are
    public int[] neurons_numbers;

    public Layer[] layers;
    public int layer_number;
    private double learn_rate = 0.1;


    public double[] errors;

    public Network(int[] neurons_numbers){
        this.layer_number = neurons_numbers.length;
        this.input_neurons_number = neurons_numbers[0];
        this.hidden_layers_number = layer_number-2;
        this.output_neurons_number = neurons_numbers[layer_number-1];
        this.neurons_numbers = neurons_numbers;


        layers = new Layer[2+hidden_layers_number];
        makeLayers();
        /*
        for(Layer lay : layers){
            System.out.print(lay.neuronNumber+"-"+lay.neuronNextNumber+"  ");

        }
        System.out.println();

         */
        input_layer = layers[0];
        output_layer = layers[layers.length-1];

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
        //else we make n-hidden layers with m-neurons

        layers[0] = new Layer(new Neuron[input_neurons_number], neurons_numbers[1]);
        for (int i = 0; i< hidden_layers_number; i++){
            layers[i+1] = new Layer(layers[i].neurons_next, neurons_numbers[i+2]);
        }
        layers[layer_number-1] = new Layer(layers[layer_number-2].neurons_next, 0); //set up output layer --> always has 0 next layers

    }



    public void learn(double[] input_neuron_values, double[] correct_neuron_values){
        if (input_neurons_number != input_neuron_values.length){
            System.out.println("Invalid input of arguments!");
        }else{
            //forward-propagation
            input_layer.setNeurons(input_neuron_values);

            for (Layer layer : layers) {
                layer.calculateNextNeurons();
            }

            //back-propagation
            calculateError(ReLUFunction(layers[layer_number-1].getNeuron_values()), correct_neuron_values);



            calculateNewWeights(correct_neuron_values);
        }
    }

    public double[] test(double[] input_neuron_values){
        if (input_neurons_number != input_neuron_values.length){
            System.out.println("Invalid input of arguments!");
            return new double[] {};
        }else{
            //forward-propagation
            input_layer.setNeurons(input_neuron_values);

            for (Layer layer : layers) {
                layer.calculateNextNeurons();
            }


            return layers[layer_number-1].getNeuron_values();
        }
    }




    public void calculateNewWeights(double[] correct_neuron_values){

        output_layer.setNeuronDeltas(output_layer.getCost(correct_neuron_values));

        for(int i = layers.length-2; i >= 0; i--){
            Neuron[] neurons = layers[i].neurons;
            Neuron[] neurons_next = layers[i].neurons_next;

            layers[i].setNeuronDeltas(layers[i].getCostHiddenLayer());

            //double[][] neuronWeights = layers[i].weights;


            //double[][] newWeights = {};

            for (Neuron neuron : neurons) {
                double neuron_value = neuron.neuron_value;
                //double [] newWeights1 = {};

                for (int k = 0; k < neurons_next.length; k++) {
                    double neuron_next_delta = neurons_next[k].delta;

                    neuron.weights[k] -= learn_rate * (neuron_next_delta * neuron_value);
                    neuron.bias -= learn_rate * neuron_next_delta;
                    //newWeights1 = Library.addDouble(newWeights1, -learnRate*(neuronNextValue/neuron_value));
                }


            }
            //layers[i].setWeights(neuronWeights);

            //calculateNewBiases(i);
        }

    }

    /*
    public void calculateNewBiases(int i){

        double[] neuronBiases = layers[i].biases;
        double[] neuronNextValues = layers[i].neuron_next_values;
        double[] neuronNextDeltas = layers[i+1].neuron_deltas;

        //double[][] newWeights = {};

        for(int j = 0; j < neuronNextValues.length-1; j++){
            neuronBiases[j] -= learn_rate * neuronNextDeltas[j];

        }
        layers[i].setBiases(neuronBiases);
        //Library.print1DoubleArray(layers[i].biases);
    }

     */




    public double calculateError(double[] output, double[] correct_output){
        double error = 0;
        for (int i = 0; i < output.length; i++){
            error += correct_output[i]-output[i];

        }
        //errors = Library.addDouble(errors, error);
        return error / output.length;
    }
    public double calculateSumError(){
        double errorSum = 0;
        for(double error : errors){
            errorSum += error;
        }
        return  errorSum/errors.length;

    }

    public void setLearn_rate(double learn_rate) {
        this.learn_rate = learn_rate;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

