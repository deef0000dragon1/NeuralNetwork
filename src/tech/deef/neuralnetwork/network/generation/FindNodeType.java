package tech.deef.neuralnetwork.network.generation;

import java.util.ArrayList;

import tech.deef.neuralnetwork.network.nodes.ConstantInputCalculationNode;
import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;
import tech.deef.neuralnetwork.network.nodes.Passthrough;

public class FindNodeType {
	public static NetworkCalculationNode findNodeType(String command, int orderID, Object o) {
		switch (command) {
		case "const0":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 0);
		case "const1":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "const2":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 2);
		case "const3":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 3);
		case "const4":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 4);
		case "const5":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 5);
		case "const6":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 6);
		case "const7":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 7);
		case "const8":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 8);
		case "const9":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 9);
		case "const10":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 10);
		case "conste":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> Math.PI);
		case "constpi":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> Math.E);
		/*
		 * case "classfetch":
		 * *********************************************************************
		 * ***************************************** // TODO implement
		 * classFetch return new NetworkCalculationNode(orderID,
		 * (ArrayList<NetworkCalculationNode> nodes) -> 1); case "typefetch": //
		 * TODO implement typeFetch return new NetworkCalculationNode(orderID,
		 * (ArrayList<NetworkCalculationNode> nodes) -> 1); case "numberfetch":
		 * // TODO implement numberFetch return new
		 * NetworkCalculationNode(orderID,
		 * command,(ArrayList<NetworkCalculationNode> nodes) -> 1); case
		 * "costeffectfetch": // TODO implement costEffectFetch return new
		 * NetworkCalculationNode(orderID, (ArrayList<NetworkCalculationNode>
		 * nodes) -> 1); case "stockeffectfetch": // TODO implement
		 * stockEffectFetch return new NetworkCalculationNode(orderID,
		 * command,(ArrayList<NetworkCalculationNode> nodes) -> 1); case
		 * "timeeffectfeth": // TODO implement timeEffectFeth return new
		 * NetworkCalculationNode(orderID, (ArrayList<NetworkCalculationNode>
		 * nodes) -> 1); case "timeeffectfetch": // TODO implement
		 * timeEffectFetch return new NetworkCalculationNode(orderID,
		 * command,(ArrayList<NetworkCalculationNode> nodes) -> 1);
		 **********************************/
		case "equalto":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() == nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "equaltorange":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() <= nodes.get(1).getValue() + nodes.get(2).getValue()
						&& nodes.get(0).getValue() >= nodes.get(1).getValue() - nodes.get(2).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "notequalto":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() != nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "notequaltor":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				// a > b+n or a < b-n
				if (nodes.get(0).getValue() > nodes.get(1).getValue() + nodes.get(2).getValue()
						|| nodes.get(0).getValue() < nodes.get(1).getValue() - nodes.get(2).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "greaterthan":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() > nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "lessthan":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() < nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "greaterthanorequalto":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() >= nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "lessthanorequalto":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() <= nodes.get(1).getValue()) {
					return 1;
				} else {
					return 0;
				}
			});
		case "add":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return (nodes.get(1).getValue() + nodes.get(0).getValue());
			});
		case "subtract":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return (nodes.get(1).getValue() - nodes.get(0).getValue());
			});
		case "multiply":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return (nodes.get(1).getValue() * nodes.get(0).getValue());
			});
		case "divide":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(1).getValue() == 0 && nodes.get(0).getValue() > 0) {
					return Double.MAX_VALUE;
				}

				if (nodes.get(1).getValue() == 0 && nodes.get(0).getValue() < 0) {
					return -Double.MAX_VALUE;
				}

				return (nodes.get(0).getValue() / nodes.get(1).getValue());
			});
		case "power":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.pow(nodes.get(0).getValue(), nodes.get(1).getValue());
			});
		case "log":// TODO implement log through log rules.
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.pow(nodes.get(0).getValue(), nodes.get(1).getValue());
			});
		case "absolutevalue":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.abs(nodes.get(0).getValue());
			});
		case "modulous":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return nodes.get(0).getValue() % nodes.get(1).getValue();
			});
		case "increment":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue() + 1);
		case "decrement":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue() - 1);
		case "invert":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(0).getValue() == 0) {
					return Double.MAX_VALUE;
				}

				return (1 / nodes.get(0).getValue());
			});
		case "opposite":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue() * -1);
		case "sum":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				double total = 0;
				for (NetworkCalculationNode node : nodes) {
					total = total + node.getValue();
				}
				return total;
			});
		case "average":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				double total = 0;
				for (NetworkCalculationNode node : nodes) {
					total = total + node.getValue();
				}
				return total / nodes.size();
			});
		case "highest":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				double high = -Double.MAX_VALUE;
				for (NetworkCalculationNode node : nodes) {
					if (node.getValue() > high) {
						high = node.getValue();
					}
				}
				return high;
			});
		case "lowest":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
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
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "or":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "not":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "nor":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "nand":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "exor":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "exnor":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> 1);
		case "leftshift":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(1) != null) {
					return ((int) nodes.get(0).getValue() << (int) nodes.get(1).getValue());
				}

				return ((int) nodes.get(0).getValue() << 1);
			});
		case "rightshift":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				if (nodes.get(1) != null) {
					return ((int) nodes.get(0).getValue() >> (int) nodes.get(1).getValue());
				}

				return ((int) nodes.get(0).getValue() >> 1);
			});
		case "tansig":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return (2 / (1 + Math.exp(-2 * nodes.get(0).getValue()))) - 1;
			});
		case "passthrough":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue());
		case "constantn":
			return new ConstantInputCalculationNode(orderID, command, null, new Passthrough(0));

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
