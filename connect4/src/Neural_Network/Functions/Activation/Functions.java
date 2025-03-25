package Neural_Network.Functions.Activation;

import java.util.Arrays;


public class Functions {

    //from -1 to 1
    public static Function tanHFunction = new Function() {
        @Override
        public double execute(double input) {
            return (Math.exp(input)-Math.exp(-input))/(Math.exp(input)+Math.exp(-input));
        }

        @Override
        public double[] execute(double[] input) {
            double[] output = new double[input.length];
            for (int i = 0; i < input.length; i++) {
                output[i] = tanHFunction.execute(input[i]);
            }
            return output;
        }
    };

    //from 0 to 1
    public static Function sigmoidFunction = new Function() {
        @Override
        public double execute(double input) {
            return 1/(1+Math.exp(-input));
        }

        @Override
        public double[] execute(double[] input) {
            double[] output = new double[input.length];
            for (int i = 0; i < input.length; i++) {
                output[i] = sigmoidFunction.execute(input[i]);
            }
            return output;
        }
    };

    //e average -> only for array
    public static Function softMaxFunction = new Function() {
        @Override
        public double execute(double input) {
            return 0;
        }

        @Override
        public double[] execute(double[] input) {
            double total = Arrays.stream(input).map(Math::exp).sum();
            double[] output = new double[input.length];
            for(int i = 0; i<output.length;i++){
                output[i] = Math.exp(input[i])/total;
            }
            return output;
        }
    };

    //max(0, value)
    public static Function ReLUFunction = new Function() {
        @Override
        public double execute(double input) {
            return Math.max(0, input);
        }

        @Override
        public double[] execute(double[] input) {
            double[] output = new double[input.length];
            for(int i = 0; i<output.length;i++){
                output[i] = Math.max(0, input[i]);
            }
            return output;
        }
    };
}

