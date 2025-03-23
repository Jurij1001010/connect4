package Neural_Network;

import java.util.Arrays;

public class Functions {




    //tanh function
    public static double tanhFunction(double input){
        //from -1 to 1
        return (Math.exp(input)-Math.exp(-input))/(Math.exp(input)+Math.exp(-input));
    }





    //sigmoid function
    public static double sigmoidFunction(double input){
        //from 0 to 1
        return 1/(1+Math.exp(-input));
    }





    //soft max function
    public static double[] softMaxFunction(double[] neuron_values){
        //e average
        double total = Arrays.stream(neuron_values).map(Math::exp).sum();
        double[] output = new double[neuron_values.length];
        for(int i = 0; i<output.length;i++){
            output[i] = Math.exp(neuron_values[i])/total;
        }
        return output;
    }
    public static double[] softMaxDerivative(double[] neuron_values){
        for (int i = 0; i < neuron_values.length; i++) {


        }

    }






    public static double[] ReLUFunction(double[] neuron_values){
        //max(0, value)
        double[] output = new double[neuron_values.length];
        for(int i = 0; i<output.length;i++){
            output[i] = Math.max(0, neuron_values[i]);
        }
        return output;
    }

    public static double ReLUFunction(double neuron_values){
        //max(0, value)
        return Math.max(0, neuron_values);
    }
}

