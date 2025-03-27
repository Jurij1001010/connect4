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
        public double execute(double value, double[] values) {
            //System.out.println("Wrong use of tanH function!");
            return tanHFunction.execute(value);
        }
    };

    //from 0 to 1
    public static Function sigmoidFunction = new Function() {
        @Override
        public double execute(double input) {
            return 1/(1+Math.exp(-input));
        }

        @Override
        public double execute(double value, double[] values) {
            //System.out.println("Wrong use of sigmoid function!");
            return sigmoidFunction.execute(value);
        }
    };

    //e average -> only for array
    public static Function softMaxFunction = new Function() {
        @Override
        public double execute(double input) {
            System.out.println("Wrong use of softMax function!");
            return 0;
        }

        @Override
        public double execute(double value, double[] values) {
            double total = Arrays.stream(values).map(Math::exp).sum();

            return Math.exp(value)/total;
        }
    };

    //e average(more precise) -> only for array
    public static Function logSoftMaxFunction = new Function() {
        @Override
        public double execute(double input) {
            System.out.println("Wrong use of logSoftMax function!");
            return 0;
        }

        @Override
        public double execute(double value, double[] values) {
            double total = Arrays.stream(values).map(Math::exp).sum();

            return Math.log(Math.exp(value)/total);
        }
    };

    //max(0, value)
    public static Function ReLUFunction = new Function() {
        @Override
        public double execute(double input) {
            return Math.max(0, input);
        }

        @Override
        public double execute(double value, double[] values) {
            //System.out.println("Wrong use of ReLU function!");
            return ReLUFunction.execute(value);
        }
    };
}

