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
	double mutationPercentile;
	String genomeSequence;
	public NetworkMutator(NeuralNetworkCalculation inputNetwork, double mutationLevel){
		network = inputNetwork;
		
		setMutationLevel(mutationLevel);
		genomeSequence = network.toString();
	}
	
	//set the level of the mutation
	public void setMutationLevel(double mutationLevel){
			mutationPercentile = mutationLevel;
	}
	
	//No Key Overload, uses current time a key. 
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
		ArrayList<Integer> randomize = new ArrayList<Integer>(nodes.size());
		//calculate and mutate the nodes tat need to be changed. 
		for(int i = 0; i <= nodes.size(); i++){
			if(rand.nextDouble() >= mutationPercentile){
				nodes.set(i, mutateNode(nodes.get(i)));
			}
		}
		
		
		//calculate the number of nodes that need to be added.
		int numToAdd = 0;
		for(int i = 0; i <= nodes.size(); i++){
			if(rand.nextDouble() >= mutationPercentile){
				numToAdd++;
				//may act as the node addition item
			}
		}
		
		nodes = addNewNodes(rand, nodes, numToAdd);
		nodes = alterNodeLinkages(rand, nodes, (int)(numToAdd*1.375));		
		//TODO: check all connections are valid and have necessary number of inputs
		//TODO: mutate connections
		
		return null;
	}
	
	//************************Mutation Methods**********************************
	
	private ArrayList<NetworkCalculationNode> alterNodeLinkages(Random rand, ArrayList<NetworkCalculationNode> nodes, int numToChange) {

		
		return null;
	}
	

	private ArrayList<NetworkCalculationNode> addNewNodes(Random rand, ArrayList<NetworkCalculationNode> nodes, int numToAdd) {
		//generate arraylist or arraylists that maps a list of the number of nodes and their connections. 
		//generate list of all nodes that deliver an output. 
		//while num is less than numToAdd
		//generate a new node. 
		//take node and find a random node to replace n of the outputs on.
		//randomly chose if the node is a fill replacement, or only a sinlge replacement.
		//replace all inputs that previous nodes had with new node. 
		//repeat. 
		
		
		//this number is the number of nodes that will be added to the node set. 
		int adding = (int) Math.pow(nodes.size(), 0.5+((rand.nextDouble()-0.5)*0.1));
		
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
	
	private int calculateNumberOfNodesToAdd(int numberOfNodes, double mutatorValue, Random rand){
		double powerFunctionOutput = (Math.pow(mutatorValue, .05))*0.615+.27; //calculates the power of the function
		double randomizerVal = (((rand.nextDouble()-0.5)*2) / 10) * powerFunctionOutput; //calculates the random power mutation
		return (int) Math.pow(numberOfNodes, (powerFunctionOutput + randomizerVal)); // calculates the total number of nodes to add
		
	}
}
