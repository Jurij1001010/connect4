package Neural_Network.Functions;

import Neural_Network.Functions.Activation.Derivative;
import Neural_Network.Functions.Activation.Derivatives;
import Neural_Network.Functions.Activation.Function;

import java.util.Objects;

public class Functions {
    public Function activationFunction;
    public Derivative activationDerivative;

    //if output layer has different activation
    public Function activationFunctionO;
    public Derivative activationDerivativeO;

    public Neural_Network.Functions.Cost.Function costFunction;
    public Neural_Network.Functions.Cost.Derivative costDerivative;

    public Functions(Function activationFunction, Neural_Network.Functions.Cost.Function costFunction){
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.ReLUFunction)){
            this.activationFunction = activationFunction;
            this.activationDerivative = Derivatives.ReLUDerivative;
        }
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.sigmoidFunction)){
            this.activationFunction = activationFunction;
            this.activationDerivative = Derivatives.sigmoidDerivative;
        }
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.tanHFunction)){
            this.activationFunction = activationFunction;
            this.activationDerivative = Derivatives.tanHDerivative;
        }
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.softMaxFunction)){
            this.activationFunction = activationFunction;
            this.activationDerivative = Derivatives.softMaxDerivative;
        }

        if(Objects.equals(costFunction, Neural_Network.Functions.Cost.Functions.cceFunction)){
            this.costFunction = costFunction;
            this.costDerivative = Neural_Network.Functions.Cost.Derivatives.ccaDerivative;
        }
    }

    public void setActivationO(Function activationFunction){
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.ReLUFunction)){
            this.activationFunctionO = activationFunction;
            this.activationDerivativeO = Derivatives.ReLUDerivative;
        }
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.sigmoidFunction)){
            this.activationFunctionO = activationFunction;
            this.activationDerivativeO = Derivatives.sigmoidDerivative;
        }
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.tanHFunction)){
            this.activationFunctionO = activationFunction;
            this.activationDerivativeO = Derivatives.tanHDerivative;
        }
        if(Objects.equals(activationFunction, Neural_Network.Functions.Activation.Functions.softMaxFunction)){
            this.activationFunctionO = activationFunction;
            this.activationDerivativeO = Derivatives.softMaxDerivative;
        }
    }
}
