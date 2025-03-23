package Neural_Network;

import java.util.Arrays;

public class Functions {
    //tanh function
    public static double tanhFunction(double input){
        return (Math.exp(input)-Math.exp(-input))/(Math.exp(input)+Math.exp(-input));
    }

    //sigmoid function
    public static double sigmoidFunction(double input){
        return 1/(1+Math.exp(-input));
    }

    //soft max function
    public static double[] softMaxFunction(double[] neuronValues){
        double total = Arrays.stream(neuronValues).map(Math::exp).sum();
        double[] output = new double[neuronValues.length];
        for(int i = 0; i<output.length;i++){
            output[i] = Math.exp(neuronValues[i])/total;
        }
        return output;
    }

    public static double[] ReLUFunction(double[] neuron_values){
        double[] output = new double[neuron_values.length];
        for(int i = 0; i<output.length;i++){
            output[i] = Math.max(0, neuron_values[i]);
        }
        return output;
    }


}
