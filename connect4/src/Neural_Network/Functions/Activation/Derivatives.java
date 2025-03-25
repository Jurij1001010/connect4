package Neural_Network.Functions.Activation;

import static Neural_Network.Functions.Activation.Functions.*;


public class Derivatives {

    public static Derivative tanHDerivative = new Derivative() {
        @Override
        public double execute(double input) {
            return 1-Math.pow((Math.exp(input)-Math.exp(-input))/(Math.exp(input)+Math.exp(-input)), 2);
        }

        @Override
        public double[] execute(double[] input) {
            double[] output = new double[input.length];
            for (int i = 0; i < input.length; i++) {
                output[i] = tanHDerivative.execute(input[i]);
            }
            return output;
        }
    };

    public static Derivative sigmoidDerivative = new Derivative() {
        @Override
        public double execute(double input) {
            double fx = 1/(1+Math.exp(-input));
            return fx*(1-fx);
        }

        @Override
        public double[] execute(double[] input) {
            double[] output = new double[input.length];
            for (int i = 0; i < input.length; i++) {
                output[i] = sigmoidDerivative.execute(input[i]);
            }
            return output;
        }
    };

    public static Derivative softMaxDerivative = new Derivative() {
        @Override
        public double execute(double input) {
            return 0;
        }

        @Override
        public double[] execute(double[] input) {
            double[] output = new double[input.length];
            double[] fx = softMaxFunction.execute(input);
            for (int i = 0; i < input.length; i++) {
                output[i] = fx[i]*(1-fx[i]);
            }
            return  output;
        }
    };

    public static Derivative ReLUDerivative = new Derivative() {
        @Override
        public double execute(double input) {
            return (input>0)?1:0;
        }
        @Override
        public double[] execute(double[] input) {
            double[] output = new double[input.length];
            for (int i = 0; i < input.length; i++) {
                output[i] = ReLUDerivative.execute(input[i]);
            }
            return output;
        }
    };

}
