package tech.deef.neuralnetwork.network.generation.mutation;

import java.util.ArrayList;
import java.util.Random;

import tech.deef.neuralnetwork.network.calculate.NeuralNetworkCalculation;
import tech.deef.neuralnetwork.network.generation.NetworkGenerator;
import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;

/** generates a new network from a given neural network
 * the only inputs are the network (in string or in physical form) and a  mutator value 
 * This mutator value is expected to be between 0 and 1.
 * the higher the mutator value, the higher the number of mutations that will occur. 
 * 
 */

public class NetworkMutator {
	NeuralNetworkCalculation network;
	private double addMutationPercentile;
	private double changeMutationPercentile;
	
	private double addNewNodesPercentile;
	private double alterNodeFunctionsPercentile;
	private double alterNodeLinkagesPercentile;
	private double removeNodePercentile;
	
	String genomeSequence;
	
	private MutationAddNewNodes addNewNodes;
	private MutationAlterNodeFunctions alterNodeFunctions;
	private MutationAlterNodeLinkages alterNodeLinkages;
	
	
	
	public MutationAddNewNodes getAddNewNodes() {
		return addNewNodes;
	}

	public void setAddNewNodes(MutationAddNewNodes addNewNodes) {
		this.addNewNodes = addNewNodes;
	}

	public MutationAlterNodeFunctions getAlterNodeFunctions() {
		return alterNodeFunctions;
	}

	public void setAlterNodeFunctions(MutationAlterNodeFunctions alterNodeFunctions) {
		this.alterNodeFunctions = alterNodeFunctions;
	}

	public MutationAlterNodeLinkages getAlterNodeLinkages() {
		return alterNodeLinkages;
	}
	

	public void setAlterNodeLinkages(MutationAlterNodeLinkages alterNodeLinkages) {
		this.alterNodeLinkages = alterNodeLinkages;
	}
	
	public double getAddMutationPercentile() {
		return addMutationPercentile;
	}

	public void setAddMutationPercentile(double addMutationPercentile) {
		this.addMutationPercentile = addMutationPercentile;
	}

	public double getChangeMutationPercentile() {
		return changeMutationPercentile;
	}
	
	public void setChangeMutationPercentile(double changeMutationPercentile) {
		this.changeMutationPercentile = changeMutationPercentile;
	}

	public double getAddNewNodesPercentile() {
		return addNewNodesPercentile;
	}

	public void setAddNewNodesPercentile(double addNewNodesPercentile) {
		this.addNewNodesPercentile = addNewNodesPercentile;
	}

	public double getAlterNodeFunctionsPercentile() {
		return alterNodeFunctionsPercentile;
	}

	public void setAlterNodeFunctionsPercentile(double alterNodeFunctionsPercentile) {
		this.alterNodeFunctionsPercentile = alterNodeFunctionsPercentile;
	}

	public double getAlterNodeLinkagesPercentile() {
		return alterNodeLinkagesPercentile;
	}

	public void setAlterNodeLinkagesPercentile(double alterNodeLinkagesPercentile) {
		this.alterNodeLinkagesPercentile = alterNodeLinkagesPercentile;
	}
	
	public double getRemoveNodePercentile() {
		return removeNodePercentile;
	}

	public void setRemoveNodePercentile(double removeNodePercentile) {
		this.removeNodePercentile = removeNodePercentile;
	}
	
	//This finction sets all of the mutation percentiles. As each number to mutate is calculated 
	//in it's own function The ability to modify the individual percentiles is not really necessary.
	//as such, all percentiles can be set here at the same time. 
	public void setMutationPercentiles( double mutationPercentiles){
		this.addNewNodesPercentile = mutationPercentiles;
		this.alterNodeFunctionsPercentile = mutationPercentiles;
		this.alterNodeLinkagesPercentile = mutationPercentiles;
		this.removeNodePercentile = mutationPercentiles;
	}

	
	
	public NetworkMutator(NeuralNetworkCalculation inputNetwork){
		genomeSequence = inputNetwork.toString();
		network = new NeuralNetworkCalculation(genomeSequence, inputNetwork.getFinalNode());
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
		
		//calculate the number of nodes that need to be changed in each if the different ways. 
		int nodesToAdd = MutationAlterNodeLinkages.calculateNumberOfNodesToAdd(network.getNetworkNodes().size(), addMutationPercentile, rand);
		int linkagesToChange = MutationAlterNodeFunctions.calculateNumberOfNodesToAdd(network.getNetworkNodes().size(), changeMutationPercentile, rand);
		int functionsToChange = MutationAddNewNodes.calculateNumberOfNodesToAdd(network.getNetworkNodes().size(), changeMutationPercentile, rand);
		int nodesToRemove = MutationRemoveNodes.calculateNumberOfNodesToRemove(network.getNetworkNodes().size(), removeNodePercentile, rand);
		
		nodes = MutationAlterNodeLinkages.alterNodeLinkages(rand, nodes, linkagesToChange);		
		nodes = MutationAlterNodeFunctions.alterNodeFunctions(rand, nodes, functionsToChange);
		nodes = MutationRemoveNodes.removeNodes(rand, nodes, nodesToRemove);
		nodes = MutationAddNewNodes.addNewNodes(rand, nodes, nodesToAdd);
		//TODO: mutate connections
		
		return null;
	}

	
	

	
}
