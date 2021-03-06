package neuralnetwork;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import neuralnetwork.activation.ActivationFunction;

/**
 * @author Stef Dijksman
 * @author Marcel Saarloos
 */
public class Neuron implements Serializable {

    private ActivationFunction af;
    private double output;
    private double weight;
    private List<Synapse> inputSynapses;
    private double error;
    private double derivative;

    public Neuron(ActivationFunction activationFunction) {
        inputSynapses = new ArrayList<Synapse>();
        af = activationFunction;
        error = 0;
    }

    public void AddInputSynapse(Synapse input) {
        inputSynapses.add(input);
    }

    public List<Synapse> GetInputSynapses() {
        return this.inputSynapses;
    }

    public double GetOutput() {
        return this.output;
    }

    public double[] GetWeights() {
        double[] weights = new double[inputSynapses.size()];

        int i = 0;
        for (Synapse synapse : inputSynapses) {
            weights[i] = synapse.GetWeight();
            i++;
        }
        return weights;
    }

    public double GetError() {
        return error;
    }
    
    public void SetError(double error) {
        this.error = error;
    }

    public void ActivateNeuron() {
        CalculateWeight();
        output = af.Activate(weight);
        derivative = af.Derivative(output);
    }

    public double GetDerivative() {
        return this.derivative;
    }

 
    private void CalculateWeight() {
        weight = 0.0;
        for (Synapse synapse : inputSynapses) {
            weight += synapse.GetWeight() * synapse.GetSourceNeuron().GetOutput();
        }
    }

    public void SetOutput(double output) {
        this.output = output;
    }

    public List<Synapse> GetInputs() {
        return this.inputSynapses;
    }
}
