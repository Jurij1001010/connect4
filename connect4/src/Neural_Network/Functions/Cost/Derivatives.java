package Neural_Network.Functions.Cost;

public class Derivatives {

    //categorical cross entropy
    public static Derivative ccaDerivative = new Derivative() {
        @Override
        public double execute(double neuron_value, double expected_value) {
            return -(expected_value)/(neuron_value*Math.log(10));
        }

        @Override
        public double[] execute(double[] neuron_values, double[] expected_values) {
            double[] output = new double[neuron_values.length];
            for (int i = 0; i < neuron_values.length; i++) {
                output[i] = ccaDerivative.execute(neuron_values[i], expected_values[i]);
            }
            return output;
        }
    };

}
