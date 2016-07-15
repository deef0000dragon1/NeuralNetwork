package tech.deef.neuralnetwork.network.nodes;

import java.util.ArrayList;

public interface Calculation {

	public double operation( ArrayList<NetworkCalculationNode> nodes);
	public double memoryValue = (Double) null;
	
}

