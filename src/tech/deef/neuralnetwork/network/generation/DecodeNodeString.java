package tech.deef.neuralnetwork.network.generation;

import tech.deef.neuralnetwork.globals.PrintingGlobals;
import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;

public class DecodeNodeString {
	//returns a network calculation node based on the string that the node is given. 
		public static NetworkCalculationNode decodeNode(String workingString) {
			int segmentSplitter = 0;
			String command = null;
			int orderID = 0;
			String inputNodes = null;
			
			
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
			NetworkCalculationNode node =FindNodeType.findNodeType(command, orderID);
			node.setInputNodeString(inputNodes);
			return node;

		}
}
