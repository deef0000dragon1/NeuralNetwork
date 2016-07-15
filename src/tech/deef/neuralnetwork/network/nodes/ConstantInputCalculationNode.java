package tech.deef.neuralnetwork.network.nodes;

public class ConstantInputCalculationNode extends NetworkCalculationNode{

	
	protected Passthrough constant;
	public ConstantInputCalculationNode(int ID, String type, Calculation function, Passthrough value) {
		super(0,type, null);
		nodeID = ID;
		needsToBeUpdated = true;
		calculateFunction = function;
		constant = value;
		// TODO Auto-generated constructor stub
	}
	

	private double calculate() {
		return constant.getValue();
	}
	
	
	
}
