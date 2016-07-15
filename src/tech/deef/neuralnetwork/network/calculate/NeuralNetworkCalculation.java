package tech.deef.neuralnetwork.network.calculate;

import java.util.ArrayList;
import java.util.Iterator;

import tech.deef.neuralnetwork.network.generation.NetworkGenerator;
import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;

public class NeuralNetworkCalculation {

	public ArrayList<NetworkCalculationNode> network;
	public int itterations;
	public double resultantValue;
	public NetworkCalculationNode outputNode;
	
	//generates the network to be calculated uses the created network and the final node. 
	private NeuralNetworkCalculation(ArrayList<NetworkCalculationNode> input, int finalNode){	
		network = input;
		
		int output = 0;
		for (int i = 0; i < network.size(); i++) {
			if (network.get(i).getNodeID() == finalNode) {
				output = i;
				break;
			}
		}
		
		outputNode = network.get(output);
	}
	
	//constructor tht takes in a string instead of a list of nodes to create the network. 
	public NeuralNetworkCalculation(String input, int finalNode){
		this(NetworkGenerator.generateNetwork(input),finalNode);
	}
	
	//generates a network using a precreated series of nodes along with the input string. 
	public NeuralNetworkCalculation(String input, ArrayList<NetworkCalculationNode> preparedNodes, int finalNode) {
		this(NetworkGenerator.generateSingleInputNetwork(input, preparedNodes),finalNode);
	}

	public void calculateNetwork(){
		calculateNetwork(1000);
	}
	
	public void calculateNetwork(int maximumItterations){
		boolean remaning = true;
		boolean outputFound = false;
		
		
		for(int i = 0; i < network.size(); i++){
			System.out.println("updating:" + i);
			network.get(i).update();
		}
		
		//itterations is created externally to be referenced by fitness calculations
		int itterations = 0;
		for(; itterations > maximumItterations; itterations++){
			remaning = false;
			
			//check if the node needs to be updated, and if it does,
			//update the node and set the remaning to true. 
			for(NetworkCalculationNode node : network){ 
				System.out.println("NodeID " + node.getNodeID());

				if(node.checkIfNeedsToBeUpdated()){
					remaning = true;
					node.update();
				}
				
				if(!outputFound && node.getNodeID() == 0){
					outputNode = node;
					outputFound = true;
				}
				
			}
			//if all nodes no longer need to be update, end the loop
			if(!remaning){
				
				break;
			}
			
			//if the nuber of itterations wanted is negative, set the 
			//number of itterations to be unlimitedd. 
			if(maximumItterations < 0){
				itterations = 0;
			}
		}
		
		
		
		resultantValue = outputNode.getValue();
		this.itterations = itterations;
		
	}
	
	//return the number of loops tha tit took the network to run.
	public int getItterations(){
		return itterations;
	}
	
	//return the final value of the node. 
	//result is always the final value of the node that has the ID 0;
	public double getResultantValue(){
		return resultantValue;
	}
	
	//returns the network in an array format for the system to calculate. 
	public ArrayList<NetworkCalculationNode> getNetworkNodes(){
		return network;
	}
	
	public String toString(){
		//System.out.println("thing");
		String Network = "[\n";
		
		Iterator<NetworkCalculationNode> ittr1 = network.iterator();
		
		while(ittr1.hasNext()){
			Network = Network + ittr1.next().toString() + "\n";
		}
		
		return Network + "]";
	}
}
