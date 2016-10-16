package tech.deef.neuralnetwork.network.generation;

import java.util.ArrayList;
import java.util.Arrays;

import tech.deef.neuralnetwork.globals.PrintingGlobals;
import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;

/*
 * given a string, generate a neural network. 
 * 
 * each node in the network is represented by a specialty character 
 * and a lettter sequence that gives the function.
 * 
 * this is followed by a numberic string that represents 
 * the identfier code for the node. Following is a list 
 * of other nodes inside a set of (), comma delimited. 
 * 
 * IE {Operation|OrderID|RandomID|(RID1,RID2,RID3,RID4)}
 * Full string is surounded by [ and ]
 * 
 * The random IDs are checked when the node is created, 
 * to make sure that no node with that ID exists. Checked externaly;
 * 
*/
public class NetworkGenerator {

	// creates a specialized network using a given input.
	// uses an arraylist of precreated nodes to be added to the generator.
	public static ArrayList<NetworkCalculationNode> generateNetwork(String listOfNodes,
																			   ArrayList<NetworkCalculationNode> inputNodeList) 
	{
		FindNodeType nodeFinder = new FindNodeType();
		ArrayList<NetworkCalculationNode> networkNodes = new ArrayList<NetworkCalculationNode>();
		ArrayList<Integer> orderIDs = new ArrayList<Integer>();
		ArrayList<String> nodeInputSources = new ArrayList<String>();

		listOfNodes = removeWhiteSpace(listOfNodes);//removes all whitespace from the string itself. 

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
				if (PrintingGlobals.PRINT_RAW_INPUT_STRING_TO_BE_PARSED) {
					System.out.println("\n" + workingString);
				}
				// get the location of the first seperator, set the command from
				// the
				// { to the first seperator, then update working
				segmentSplitter = workingString.indexOf('|');
				command = workingString.substring(1, segmentSplitter);
				workingString = workingString.substring(segmentSplitter);

				if (PrintingGlobals.PRINT_COMMAND_ORDER_INPUT_PARSED_INFORMATION) {
					System.out.println("C " + command);
				}
				// get the location of the seperator, set the orderID from the
				// { to the first seperator, then update working
				segmentSplitter = workingString.substring(1).indexOf('|');
				orderID = Integer.parseInt(workingString.substring(1, segmentSplitter + 1));
				workingString = workingString.substring(segmentSplitter + 1);

				if (PrintingGlobals.PRINT_COMMAND_ORDER_INPUT_PARSED_INFORMATION) {
					System.out.println("O " + orderID);
				}
				// get the location of the first seperator, set the Nodes from
				// the
				// { to the first seperator, then update working
				inputNodes = workingString.substring(1, workingString.length() - 1);

				if (PrintingGlobals.PRINT_COMMAND_ORDER_INPUT_PARSED_INFORMATION) {
					System.out.println("I " + inputNodes);
				}
				// figure out the propper command to create.
				// TODO determine how to give the system a passthrough from this
				// point that can be used
				// as the set value system for the inputs.
				networkNodes.add(nodeFinder.findNodeType(command, orderID, null));

				// add to lists
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

		// add the given input nodes to the list of nodes wanted if not null.
		if(inputNodeList != null){
			for (NetworkCalculationNode extraNode : inputNodeList) {
				networkNodes.add(extraNode);
				orderIDs.add(extraNode.getNodeID());
				nodeInputSources.add(extraNode.getInputNodes());
			}
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
					
					int temp = orderIDs.indexOf(orderIDFromNode);
					//get the index from the input node random id
					
					nodesToBeAddedAsSources.add(networkNodes.get(temp));
					//add the node as an input node using sid index
				}
			}

			networkNodes.get(i).setNodes(nodesToBeAddedAsSources);
			//set the input nodes using the list gotten

		}

		// loop through and add the inputs directally to the list.

		return networkNodes;

	}

	public static ArrayList<NetworkCalculationNode> generateNetwork(String listOfNodes) {
		return generateNetwork(listOfNodes, null);
	}

	
	
	/**
	 * @param listOfNodes String removes all whitespace
	 * @return String 
	 * 
	 * */
	private static String removeWhiteSpace(String listOfNodes) {

		return listOfNodes.replaceAll("\\s+", "");
	}

	/** 
	 * @param nodeIDs
	 * 				the string that contains the input node ids 
	 * 				the string has a form of (n1,n2...n3,n4) 
	 * 
	 * @return ArrayList<integer> returns an arraylist of the input integers. 
	*/
	private static ArrayList<Integer> ParseNodeSources(String nodeIDs) {

		if (!nodeIDs.equals("()")) {
			// prints the nodes to be attempted to be added to the array list of
			// integers.
			if (PrintingGlobals.PRINT_SETTING_INPUT_NODES_FOR_NODE_INFORATION) {
				System.out.print("\nNIDs " + nodeIDs + ": ");
			}
			ArrayList<Integer> parsedNodes = new ArrayList<Integer>();
			ArrayList<String> unparsedNodes = new ArrayList<String>(
					Arrays.asList(nodeIDs.substring(1, nodeIDs.length() - 1).split(",")));

			for (int i = 0; i < unparsedNodes.size(); i++) {
				// this print output the node connection id as it was added to
				// parsed nodes.
				if (PrintingGlobals.PRINT_SETTING_INPUT_NODES_FOR_NODE_INFORATION) {
					System.out.print(unparsedNodes.get(i) + ": ");
				}
				parsedNodes.add(Integer.parseInt(unparsedNodes.get(i)));
			}


			return parsedNodes;
		}
		return null;
	}

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
		// remove all excess characters excluding those of the first "[" and "]"
		
		boolean in = false;
		int start = 0;
		int end = 0;
		for (int i = 0; i < genome.length(); i++) {
			//loops through looking for the next '{'or'}'
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
		return seperatedNodes;
	}

	
}
