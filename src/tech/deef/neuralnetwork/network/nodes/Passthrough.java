package tech.deef.neuralnetwork.network.nodes;

public class Passthrough {

	private double value;
	public Passthrough(double input){
		value = input;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double input){
		value = input;
	}
}
