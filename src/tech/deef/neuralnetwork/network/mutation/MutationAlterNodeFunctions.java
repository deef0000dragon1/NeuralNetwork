package tech.deef.neuralnetwork.network.mutation;

import java.util.ArrayList;
import java.util.Random;

import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;

public class MutationAlterNodeFunctions {

	public static ArrayList<NetworkCalculationNode> alterNodeFunctions(Random rand,
			ArrayList<NetworkCalculationNode> nodes, int functionsToChange) {
		// TODO Auto-generated method stub
		return null;
	}

	private final static double MUTATOR = 0.2830554;
	private final static double MULTIPLIER = 0.7105625;
	private final static double ADDER = 0.1744375;
	public static int calculateNumberOfNodesToAdd(int numberOfNodes, double mutatorValue, Random rand){
		double powerFunctionOutput = (Math.pow(mutatorValue, MUTATOR))*MULTIPLIER+ADDER; //calculates the power of the function
		double randomizerVal = (((rand.nextDouble()-0.5)*2) / 10) * powerFunctionOutput; //calculates the random power mutation
		return (int) Math.pow(numberOfNodes, (powerFunctionOutput + randomizerVal)); // calculates the total number of nodes to add
		
	}

}
