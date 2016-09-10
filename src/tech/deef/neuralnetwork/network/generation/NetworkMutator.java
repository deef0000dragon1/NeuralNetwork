package tech.deef.neuralnetwork.network.generation;

import java.util.ArrayList;
import java.util.Random;

import tech.deef.neuralnetwork.network.calculate.NeuralNetworkCalculation;
import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;

/** generates a new network from a given neural network
 * the only inputs are the network (in string or in physical form) and a  mutator value 
 * This mutator value is expected to be between 0 and 1.
 * the higher the mutator value, the higher the number of mutations that will occur. 
 * 
 */

public class NetworkMutator {
	NeuralNetworkCalculation network;
	double addMutationPercentile;
	double changeMutationPercentile;
	String genomeSequence;
	
	public double getAddMutationPercentile() {
		return addMutationPercentile;
	}

	public double getChangeMutationPercentile() {
		return changeMutationPercentile;
	}

	public void setAddMutationPercentile(double addMutationPercentile) {
		this.addMutationPercentile = addMutationPercentile;
	}

	public void setChangeMutationPercentile(double changeMutationPercentile) {
		this.changeMutationPercentile = changeMutationPercentile;
	}
	
	
	
	public NetworkMutator(NeuralNetworkCalculation inputNetwork, double addLevel, double changeLevel){
		network = inputNetwork;
		
		setMutationLevel(addLevel, changeLevel);
		genomeSequence = network.toString();
	}
	
	//set the level of the mutation
	public void setMutationLevel(double addLevel, double changeLevel){
			addMutationPercentile = addLevel;
			changeMutationPercentile = changeLevel;
	}
	
	//No-key Overload, uses current time a key. 
	public String startMutation(){
		return startMutation((int) System.currentTimeMillis());
	}
	
	
	
	/**
	 * calculates a new network from the given network using the mutationLevel
	 * uses key as the random number generator seed. 
	 *
	 * CURRENT GOAL:
	 *		mutates current nodes
	 *		adds new nodes in line
	 *		Rearranges current node linkages. 
	 *
	 * TODO: add removeal posibility.
	 * */
	public String startMutation(int key){
		Random rand = new Random(key);
		
		ArrayList<NetworkCalculationNode> nodes = network.getNetworkNodes();
		
		//calculate the number of nodes that need to be added.
		int nodesToAdd = calculateNumberOfNodesToAdd(network.getNetworkNodes().size(), addMutationPercentile, rand);
		int linkagesToChange = calculateNumberOfNodesToAdd(network.getNetworkNodes().size(), changeMutationPercentile, rand);
		int functionsToChange = calculateNumberOfNodesToAdd(network.getNetworkNodes().size(), changeMutationPercentile, rand);
		
		nodes = addNewNodes(rand, nodes, nodesToAdd);
		nodes = alterNodeLinkages(rand, nodes, linkagesToChange);		
		nodes = alterNodeFunctions(rand, nodes, functionsToChange);
		//TODO: mutate connections
		
		return null;
	}
	

	//how to mutate
	//add new nodes
		//calculate number to add
		//add
			//random node
			//random connections
	//randomize current node linkages
	//randomize current node types
	//clean up function
	
	
	//************************Mutation Methods**********************************
	
	private ArrayList<NetworkCalculationNode> addNewNodes(Random rand, ArrayList<NetworkCalculationNode> nodes, int numToAdd) {
		//this number is the number of nodes that will be added to the node set. 		
		for(int i = 0; i < numToAdd; i++){
			nodes = addNewNode(nodes);
			//cenerate lists in this function and pass them into the next one.
		}
		
		return nodes;
	}

	private ArrayList<NetworkCalculationNode> alterNodeLinkages(Random rand, ArrayList<NetworkCalculationNode> nodes, int numToChange) {
		//generate arraylist or arraylists that maps a list of the number of nodes and their connections. 
				//generate list of all nodes that deliver an output. 
				//while num is less than numToAdd
				//generate a new node. 
				//take node and find a random node to replace n of the outputs on.
				//randomly chose if the node is a fill replacement, or only a sinlge replacement.
				//replace all inputs that previous nodes had with new node. 
				//repeat. 
		
		return null;
	}
	
	private ArrayList<NetworkCalculationNode> alterNodeFunctions(Random rand, ArrayList<NetworkCalculationNode> nodes,
			int functionsToChange) {
		// TODO create alter nodeFunctions function
		return null;
	}
	
	private ArrayList<NetworkCalculationNode> addNewNode(ArrayList<NetworkCalculationNode> nodes) {
		// TODO Create add new node function
		return null;
	}
	
	/**
	 * takes in a node,
	 * gets the node type
	 * using the default typing table, changes the node, or changes the node completely
	 * 
	 * */
	private NetworkCalculationNode mutateNode(NetworkCalculationNode networkCalculationNode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final static double MUTATOR = 0.2830554;
	private final static double MULTIPLIER = 0.7105625;
	private final static double ADDER = 0.1744375;
	private int calculateNumberOfNodesToAdd(int numberOfNodes, double mutatorValue, Random rand){
		double powerFunctionOutput = (Math.pow(mutatorValue, MUTATOR))*MULTIPLIER+ADDER; //calculates the power of the function
		double randomizerVal = (((rand.nextDouble()-0.5)*2) / 10) * powerFunctionOutput; //calculates the random power mutation
		return (int) Math.pow(numberOfNodes, (powerFunctionOutput + randomizerVal)); // calculates the total number of nodes to add
		
	}

	
}
