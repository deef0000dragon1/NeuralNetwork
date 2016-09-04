package tech.deef.neuralnetwork.network.generation;

import java.util.ArrayList;
import java.util.Arrays;

import tech.deef.neuralnetwork.network.nodes.*;

/*
 * given a string, generate a neural network. 
 * each node in the network is represented by a specialty character and a lettter sequence that gives the function.
 * this is followed by a numberic string that represents the identfier code for the node. Following is a list of other nodes inside a set of (), comma delimited. 
 * IE {Operation|OrderID|RandomID|(RID1,RID2,RID3,RID4)}
 * Full string is surounded by [ and ]
 * 
 * The random IDs are checked when the node is created, to make sure that no node with that ID exists. Checked externaly;
 * 
*/
public class NetworkGenerator {

	// creates a specialized network using a given input.
	// uses an arraylist of precreated nodes to be added to the generator.
	public static ArrayList<NetworkCalculationNode> generateSingleInputNetwork(String listOfNodes, ArrayList<NetworkCalculationNode> inputNodeList) {
		
		ArrayList<NetworkCalculationNode> networkNodes = new ArrayList<NetworkCalculationNode>();
		ArrayList<Integer> orderIDs = new ArrayList<Integer>();
		ArrayList<String> nodeInputSources = new ArrayList<String>();

		listOfNodes = removeWhiteSpace(listOfNodes);

		ArrayList<String> stringFormatNodes = new ArrayList<String>();
		stringFormatNodes = seperateNodeStrings(listOfNodes);
		// loop variables

		String workingString;
		String command;
		int orderID;
		String inputNodes;
		int segmentSplitter;

		// loops through all nodes and creates them.
		for (int i = 0; i < stringFormatNodes.size(); i++) {

			try {
				// remove whitespace

				// get the current node string
				workingString = stringFormatNodes.get(i);
				System.out.println("\n" + workingString);

				// get the location of the first seperator, set the command from
				// the
				// { to the first seperator, then update working
				segmentSplitter = workingString.indexOf('|');
				command = workingString.substring(1, segmentSplitter);
				workingString = workingString.substring(segmentSplitter);

				System.out.println("C " + command);

				// get the location of the seperator, set the orderID from the
				// { to the first seperator, then update working
				segmentSplitter = workingString.substring(1).indexOf('|');
				orderID = Integer.parseInt(workingString.substring(1, segmentSplitter + 1));
				workingString = workingString.substring(segmentSplitter + 1);

				System.out.println("O " + orderID);

				

				// get the location of the first seperator, set the Nodes from
				// the
				// { to the first seperator, then update working
				inputNodes = workingString.substring(1, workingString.length() - 1);

				System.out.println("I " + inputNodes);

				// figure out the propper command to create.
				// TODO determine how to give the system a passthrough from this
				// point that can be used
				// as the set value system for the inputs.
				networkNodes.add(findNodeType(command, orderID, null));

				//add to lists 
				orderIDs.add(orderID);
				nodeInputSources.add(inputNodes);

			} catch (NumberFormatException e) {
				System.out.flush();
				e.printStackTrace();
				// ignored.
			} catch (NullPointerException e) {
				// also ignored Presuming that there is an error in creation,
				// but will handle code for
				System.out.flush();
				e.printStackTrace();

			}

		}
		
		//add the given input nodes to the list of nodes wanted. 
		
		for(NetworkCalculationNode extraNode: inputNodeList){
			networkNodes.add(extraNode);
			orderIDs.add(extraNode.getNodeID());
			nodeInputSources.add(extraNode.getInputNodes());
		}
		
		// loop trough all nodes and add the necessary node sources to the list
		// of nodes.
		for (int i = 0; i < networkNodes.size(); i++) {
			// get string of nodes with random ID
			// parse into list of node ids
			// take nodeIDs and loop through them, adding the actual nodes to a
			// list
			// add the list of nodes to the node itself.

			ArrayList<Integer> singleNodeInputSources = ParseNodeSources(nodeInputSources.get(i));
			ArrayList<NetworkCalculationNode> nodesToBeAddedAsSources = new ArrayList<NetworkCalculationNode>();

			if (singleNodeInputSources != null) {
				for (Integer orderIDFromNode : singleNodeInputSources) {
					int temp = orderIDs.indexOf(orderIDFromNode);//get the index of the input node from the random id
					nodesToBeAddedAsSources.add(networkNodes.get(temp));//add the node as an input using said index.
				}
			}

			networkNodes.get(i).setNodes(nodesToBeAddedAsSources);//set the inpu nodes using the list gotten.

		}

		// loop through and add the inputs directally to the list.

		return networkNodes;	


	}

	public static ArrayList<NetworkCalculationNode> generateNetwork(String listOfNodes) {
		ArrayList<NetworkCalculationNode> networkNodes = new ArrayList<NetworkCalculationNode>();
		ArrayList<Integer> orderIDs = new ArrayList<Integer>();
		ArrayList<String> nodeInputSources = new ArrayList<String>();

		listOfNodes = removeWhiteSpace(listOfNodes);

		ArrayList<String> stringFormatNodes = new ArrayList<String>();
		stringFormatNodes = seperateNodeStrings(listOfNodes);
		// loop variables

		String workingString;
		String command;
		int orderID;
		String inputNodes;
		int segmentSplitter;

		// loops through all nodes and creates them.
		for (int i = 0; i < stringFormatNodes.size(); i++) {

			try {
				// remove whitespace

				// get the current node string
				workingString = stringFormatNodes.get(i);
				System.out.println("\n" + workingString);

				// get the location of the first seperator, set the command from
				// the
				// { to the first seperator, then update working
				segmentSplitter = workingString.indexOf('|');
				command = workingString.substring(1, segmentSplitter);
				workingString = workingString.substring(segmentSplitter);

				System.out.println("C " + command);

				// get the location of the seperator, set the orderID from the
				// { to the first seperator, then update working
				segmentSplitter = workingString.substring(1).indexOf('|');
				orderID = Integer.parseInt(workingString.substring(1, segmentSplitter + 1));
				workingString = workingString.substring(segmentSplitter + 1);

				System.out.println("O " + orderID);

				

				// get the location of the first seperator, set the Nodes from
				// the
				// { to the first seperator, then update working
				inputNodes = workingString.substring(1, workingString.length() - 1);

				System.out.println("I " + inputNodes);

				// figure out the propper command to create.
				// TODO determine how to give the system a passthrough from this
				// point that can be used
				// as the set value system for the inputs.
				networkNodes.add(findNodeType(command, orderID, null));

				//add to lists 
				orderIDs.add(orderID);
				nodeInputSources.add(inputNodes);

			} catch (NumberFormatException e) {
				System.out.flush();
				e.printStackTrace();
				// ignored.
			} catch (NullPointerException e) {
				// also ignored Presuming that there is an error in creation,
				// but will handle code for
				System.out.flush();
				e.printStackTrace();

			}

		}
		
		//add the given input nodes to the list of nodes wanted. 
		

		
		// loop trough all nodes and add the necessary node sources to the list
		// of nodes.
		for (int i = 0; i < networkNodes.size(); i++) {
			// get string of nodes with random ID
			// parse into list of node ids
			// take nodeIDs and loop through them, adding the actual nodes to a
			// list
			// add the list of nodes to the node itself.

			ArrayList<Integer> singleNodeInputSources = ParseNodeSources(nodeInputSources.get(i));
			ArrayList<NetworkCalculationNode> nodesToBeAddedAsSources = new ArrayList<NetworkCalculationNode>();

			if (singleNodeInputSources != null) {
				for (Integer orderIDFromNode : singleNodeInputSources) {
					int temp = orderIDs.indexOf(orderIDFromNode);//get the index of the input node from the random id
					nodesToBeAddedAsSources.add(networkNodes.get(temp));//add the node as an input using said index.
				}
			}

			networkNodes.get(i).setNodes(nodesToBeAddedAsSources);//set the inpu nodes using the list gotten.

		}

		// loop through and add the inputs directally to the list.

		return networkNodes;
	}

	// removes all whitespace from the noces
	private static String removeWhiteSpace(String listOfNodes) {

		return listOfNodes.replaceAll("\\s+", "");
	}

	// takes in a string of nodeIDs (n1,n2,n3,n4) and returns a list of integers
	// representing that set of nodes.
	private static ArrayList<Integer> ParseNodeSources(String nodeIDs) {

		if (!nodeIDs.equals("()")) {
			//prints the nodes to be attempted to be added to the array list of integers.
			//System.out.print("NIDs " + nodeIDs + ": ");
			ArrayList<Integer> parsedNodes = new ArrayList<Integer>();
			ArrayList<String> unparsedNodes = new ArrayList<String>(
					Arrays.asList(nodeIDs.substring(1, nodeIDs.length() - 1).split(",")));

			for (int i = 0; i < unparsedNodes.size(); i++) {
				//this print output the node connection id as it was added to parsed nodes. 
				//System.out.print(unparsedNodes.get(i) + ": ");
				parsedNodes.add(Integer.parseInt(unparsedNodes.get(i)));
			}

			//newline for seperation
			//System.out.println("\n");

			return parsedNodes;
		}
		return null;
	}

	// sepearte the nodes into a list of strings
	// create all of the nodes
	// add nodes to a list
	// add id to a list with paralell index
	// add list of input nodes to a list with a paralell index
	// using the created nodes, udate the nodes to the

	/**
	 * 
	 * @param genome
	 *            String string representing the entire neural network and
	 *            connections Held in the format [{d}{d}{d}{d}{d}{d}{d}{d}{d}]
	 * @return an ArrayList<String> of all sets of nodes and connections each
	 *         set of characters in the arraylist is a set between '{' and '}'
	 *         error handeling is expected to be taken care of by the node
	 *         sorter
	 */
	public static ArrayList<String> seperateNodeStrings(String genome) {
		ArrayList<String> seperatedNodes = new ArrayList<String>();

		genome = genome.substring(genome.indexOf('['), genome.indexOf(']') + 1);
		// remove all characters excluding those oof the first "[" and "]"

		// if (genome.charAt(0) == '[' && genome.charAt(genome.length() - 1) ==
		// ']') {
		boolean in = false;
		int start = 0;
		int end = 0;
		for (int i = 0; i < genome.length(); i++) {
			if ((genome.charAt(i) == '{') && !in) {
				start = i;
				in = true;
			}
			if ((genome.charAt(i) == '}') && in) {
				end = i;
				in = false;
				seperatedNodes.add(genome.substring(start, end + 1));
			}
		}

		// }

		return seperatedNodes;

	}

	private static NetworkCalculationNode findNodeType(String command, int orderID, Object o) {
		switch (command) {
		case "const0":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 0);
		case "const1":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "const2":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 2);
		case "const3":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 3);
		case "const4":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 4);
		case "const5":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 5);
		case "const6":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 6);
		case "const7":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 7);
		case "const8":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 8);
		case "const9":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 9);
		case "const10":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 10);
		case "conste":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> Math.PI);
		case "constpi":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> Math.E);
		/*
		 * case "classfetch":
		 * *********************************************************************
		 * ***************************************** // TODO implement
		 * classFetch return new NetworkCalculationNode(orderID,
		 * (ArrayList<NetworkCalculationNode> nodes) -> 1); case "typefetch": //
		 * TODO implement typeFetch return new NetworkCalculationNode(orderID,
		 * (ArrayList<NetworkCalculationNode> nodes) -> 1); case "numberfetch":
		 * // TODO implement numberFetch return new
		 * NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode>
		 * nodes) -> 1); case "costeffectfetch": // TODO implement
		 * costEffectFetch return new NetworkCalculationNode(orderID,
		 * (ArrayList<NetworkCalculationNode> nodes) -> 1); case
		 * "stockeffectfetch": // TODO implement stockEffectFetch return new
		 * NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode>
		 * nodes) -> 1); case "timeeffectfeth": // TODO implement timeEffectFeth
		 * return new NetworkCalculationNode(orderID,
		 * (ArrayList<NetworkCalculationNode> nodes) -> 1); case
		 * "timeeffectfetch": // TODO implement timeEffectFetch return new
		 * NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode>
		 * nodes) -> 1);
		 **********************************/
		case "equalto":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() == nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "equaltorange":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() <= nodes.get(1).getValue() + nodes.get(2).getValue()
						&& nodes.get(0).getValue() >= nodes.get(1).getValue() - nodes.get(2).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "notequalto":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() != nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "notequaltor":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				// a > b+n or a < b-n
				if (nodes.get(0).getValue() > nodes.get(1).getValue() + nodes.get(2).getValue()
						|| nodes.get(0).getValue() < nodes.get(1).getValue() - nodes.get(2).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "greaterthan":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() > nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "lessthan":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() < nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "greaterthanorequalto":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() >= nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "lessthanorequalto":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() <= nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "add":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				return (nodes.get(1).getValue() + nodes.get(0).getValue());
			});
		case "subtract":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				return (nodes.get(1).getValue() - nodes.get(0).getValue());
			});
		case "multiply":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				return (nodes.get(1).getValue() * nodes.get(0).getValue());
			});
		case "divide":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(1).getValue() == 0 && nodes.get(0).getValue() > 0) {
					return Double.MAX_VALUE;
				}

				if (nodes.get(1).getValue() == 0 && nodes.get(0).getValue() < 0) {
					return -Double.MAX_VALUE;
				}

				return (nodes.get(0).getValue() / nodes.get(1).getValue());
			});
		case "power":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.pow(nodes.get(0).getValue(), nodes.get(1).getValue());
			});
		case "log":// TODO implement log through log rules.
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.pow(nodes.get(0).getValue(), nodes.get(1).getValue());
			});
		case "absolutevalue":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.abs(nodes.get(0).getValue());
			});
		case "modulous":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				return nodes.get(0).getValue() % nodes.get(1).getValue();
			});
		case "increment":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue() + 1);
		case "decrement":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue() - 1);
		case "invert":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() == 0) {
					return Double.MAX_VALUE;
				}

				return (1 / nodes.get(0).getValue());
			});
		case "opposite":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue() * -1);
		case "sum":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				double total = 0;
				for (NetworkCalculationNode node : nodes) {
					total = total + node.getValue();
				}
				return total;
			});
		case "average":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				double total = 0;
				for (NetworkCalculationNode node : nodes) {
					total = total + node.getValue();
				}
				return total / nodes.size();
			});
		case "highest":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				double high = -Double.MAX_VALUE;
				for (NetworkCalculationNode node : nodes) {
					if (node.getValue() > high) {
						high = node.getValue();
					}
				}
				return high;
			});
		case "lowest":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				double high = Double.MAX_VALUE;
				for (NetworkCalculationNode node : nodes) {
					if (node.getValue() < high) {
						high = node.getValue();
					}
				}
				return high;
			});
		case "roundup":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> ((int) nodes.get(0).getValue() + 1));
		case "rounddown":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> ((int) nodes.get(0).getValue()));
		case "roundnearest":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> Math.round(nodes.get(0).getValue()));
		case "sin":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> Math.sin(nodes.get(0).getValue()));
		case "cos":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> Math.cos(nodes.get(0).getValue()));
		case "tan":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> Math.tan(nodes.get(0).getValue()));
		case "sec":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> 1 / Math.cos((nodes.get(0).getValue())));
		case "cosec":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> 1 / Math.sin(nodes.get(0).getValue()));
		case "cotan":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> 1 / Math.tan(nodes.get(0).getValue()));
		case "arcsin":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> Math.asin(nodes.get(0).getValue()));
		case "arccos":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> Math.acos(nodes.get(0).getValue()));
		case "arctan":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> Math.atan(nodes.get(0).getValue()));
		case "and":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "or":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "not":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "nor":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "nand":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "exor":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "exnor":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "leftshift":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(1) != null) {
					return ((int) nodes.get(0).getValue() << (int) nodes.get(1).getValue());
				}

				return ((int) nodes.get(0).getValue() << 1);
			});
		case "rightshift":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(1) != null) {
					return ((int) nodes.get(0).getValue() >> (int) nodes.get(1).getValue());
				}

				return ((int) nodes.get(0).getValue() >> 1);
			});
		case "tansig":
			return new NetworkCalculationNode(orderID, command,(ArrayList<NetworkCalculationNode> nodes) -> {
				return (2 / (1 + Math.exp(-2 * nodes.get(0).getValue()))) - 1;
			});
		case "passthrough":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue());
		case "constantn":
			return new ConstantInputCalculationNode(orderID, command,null, new Passthrough(0));

		/*
		 * case "register": *************NOT IMPLEMENTABLE IN CURRENT
		 * FORMAT******************************* Calculation t =
		 * (ArrayList<NetworkCalculationNode> nodes, memoryValue) -> {
		 * if(nodes.get(1).getValue() == 0){ memoryValue =
		 * nodes.get(0).getValue(); } return memoryValue; }; return new
		 * NetworkCalculationNode(orderID, command,t);
		 */
		default:
			// error log.
			return new NetworkCalculationNode(orderID, "passthrough",
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue());
		}

	}
}
