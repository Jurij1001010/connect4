package Neural_Network.Functions.Activation;

import static Neural_Network.Functions.Activation.Functions.*;


public class Derivatives {

    public static Derivative tanHDerivative = new Derivative() {
        @Override
        public double execute(double input) {
            double output =1-Math.pow((Math.exp(input)-Math.exp(-input))/(Math.exp(input)+Math.exp(-input)), 2);
            if(Double.isNaN(output)){
                System.out.println("tanH derivative NaN");
            }
            return output;
        }

        @Override
        public double execute(double value, double[] values) {

            return tanHDerivative.execute(value);
        }
    };

    public static Derivative sigmoidDerivative = new Derivative() {
        @Override
        public double execute(double input) {
            double fx = 1/(1+Math.exp(-input));
            double output = fx*(1-fx);

            if(Double.isNaN(output)){
                System.out.println("sigmoid derivative NaN");
            }

            return output;
        }

        @Override
        public double execute(double value, double[] values) {

            return sigmoidDerivative.execute(value);
        }
    };

    public static Derivative softMaxDerivative = new Derivative() {
        @Override
        public double execute(double input) {
            System.out.println("Wrong use of softMax derivative!");
            return 0;
        }

        @Override
        public double execute(double value, double[] values) {

            double fx = softMaxFunction.execute(value, values);

            double output = fx*(1-fx);

            if(Double.isNaN(output)){
                System.out.println("softmax derivative NaN");
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
        public double execute(double value, double[] values) {
            return ReLUDerivative.execute(value);
        }
    };

}
