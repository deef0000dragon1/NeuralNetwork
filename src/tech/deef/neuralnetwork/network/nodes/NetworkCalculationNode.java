package tech.deef.neuralnetwork.network.nodes;

import java.util.ArrayList;
import java.util.Iterator;

import tech.deef.neuralnetwork.globals.PrintingGlobals;

public class NetworkCalculationNode {

	protected ArrayList<NetworkCalculationNode> inputNodes;
	protected ArrayList<Double> inputNodePreviousValues;
	protected boolean needsToBeUpdated;
	protected double value;
	protected int nodeID;
	protected Calculation calculateFunction;
	protected String nodeType;
	protected String InputNodeString;

	// node constructor.
	// given the ids for each item and the function. builds it and sets update
	// to false.
	// sets the input calue of all previous nodes to be null
	public NetworkCalculationNode(int ID, String type, Calculation function) {
		nodeID = ID;
		nodeType = type;
		needsToBeUpdated = true;
		calculateFunction = function;

	}

	// sets the node inputs. Used for the final equation. may need to update
	// calculationFunction as well.
	public void setNodes(ArrayList<NetworkCalculationNode> nodes) {
		// this print informs what node is having it's nodes set.
		if (PrintingGlobals.PRINT_SETTING_INPUT_NODES_FOR_NODE_INFORATION) {
			System.out.println("\nset nodes for node " + nodeID);
		}
		inputNodes = nodes;
		inputNodePreviousValues = new ArrayList<Double>(inputNodes.size());

		// set all of the previous node inputs to 0 to prevent NPE's.
		for (NetworkCalculationNode node : inputNodes) {
			inputNodePreviousValues.add(0.0);
		}

	}

	// updates the node to the correct values nd sets to be updated to false.
	// updates node previous values.
	public void update() {
		value = calculate();// update
		needsToBeUpdated = false;// tell it it no longer needs to be updated
		for (int i = 0; i < inputNodes.size(); i++) { // set the previous values
			if (inputNodePreviousValues.size() != 0) {// for checking later.
				inputNodePreviousValues.set(i, inputNodes.get(i).getValue());
			}
		}
	}

	// calculates the physical vlue according to the input lambda equation.
	private double calculate() {
		return calculateFunction.operation(inputNodes);
	}

	// checks all input values, and should even one value not be equal to the
	// previous value
	// the node needs to be updated, thus update is set to true.
	public boolean checkIfNeedsToBeUpdated() {
		needsToBeUpdated = true;
		for (int i = 0; i < inputNodes.size(); i++) {

			// TODO determine why the input node previous values is not updating
			if (inputNodePreviousValues.size() != 0) {
				double inputNodeVal = inputNodes.get(i).getValue();
				double inputNodePreviousValue = inputNodePreviousValues.get(i);
				if (inputNodeVal != inputNodePreviousValue) {
					needsToBeUpdated = true;
					return needsToBeUpdated;
				}
			} else {
				break;
			}
		}
		needsToBeUpdated = false;
		return needsToBeUpdated;
	}

	public double getValue() {
		return value;
	}

	public int getNodeID() {
		return nodeID;
	}

	public String getNodeType() {
		return nodeType;
	}

	public ArrayList<NetworkCalculationNode> getInputs() {
		return inputNodes;
	}

	// (node,Node,Node)
	public String getInputNodes() {
		String nodeString = "(";
		if (inputNodes.size() != 0) {
			for (int i = 0; i < inputNodes.size(); i++) {
				nodeString += inputNodes.get(i).getNodeID();
				if (i < inputNodes.size() - 1) {
					nodeString += ",";
				}
			}
		}

		return nodeString + ")";
	}

	// retruns the string seed sequence of the node.
	public String toString() {
		String nodeStrings = "";

		Iterator<NetworkCalculationNode> ittr1 = getInputs().iterator();

		while (ittr1.hasNext()) {
			nodeStrings = nodeStrings + ittr1.next().getNodeID() + ",";
		}

		if (!getInputs().isEmpty()) {
			nodeStrings = nodeStrings.substring(0, nodeStrings.length() - 1);
		}
		return "{" + getNodeType() + "|" + getNodeID() + "|(" + nodeStrings + ")}";
	}

	public String getInputNodeString() {
		return InputNodeString;
	}

	public void setInputNodeString(String inputNodeString) {
		InputNodeString = inputNodeString;
	}
}
