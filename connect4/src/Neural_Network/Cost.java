package Neural_Network;

public class Cost {
    public double cost;
    public double[] neuron_values;
    public double[] expected_values;
    private final int neuron_number;


    public Cost(double[] neuron_values, double[] expected_values){
        this.neuron_values = neuron_values;
        this.expected_values = expected_values;
        neuron_number = this.neuron_values.length;
        calculateCost();
    }

    public void calculateCost(){
        //gets loss with categorical cross entropy
        //ONLY IN LAST LAYER
        for(int i = 0; i< neuron_number; i++){
            cost += neuron_values[i]*Math.log(neuron_values[i]);
        }
        cost = -cost;
    }

    public double costDerivative(double expected_value, double neuron_value){
        //derivative of categorical cross entropy
        return -(expected_value/(neuron_value*Math.log(10)));
    }


    public double[] getDeltasOutputLayer(){
        double[] costs = new double[neuron_number];
        for (int i = 0; i < neuron_number; i++) {
            double cost = costDerivative(expected_values[i], neuron_values[i])*
        }
    }


}
