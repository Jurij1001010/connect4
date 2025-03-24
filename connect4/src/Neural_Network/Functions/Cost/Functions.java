package Neural_Network.Functions.Cost;



public class Functions {

    //categorical cross entropy
    public static Function cceFunction = new Function() {
        @Override
        public double execute(double[] neuron_values, double[] expected_values) {
            double cost = 0;
            for(int i = 0; i < neuron_values.length; i++){
                cost += expected_values[i]*Math.log(neuron_values[i]);
            }
            return -cost;
        }
    };


}
