package tech.deef.neuralnetwork.network.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tech.deef.neuralnetwork.network.nodes.Calculation;
import tech.deef.neuralnetwork.network.nodes.ConstantInputCalculationNode;
import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;
import tech.deef.neuralnetwork.network.nodes.Passthrough;
	
	
	

public class FindNodeType {
	
	
	private Map<String, Calculation> nodeTypeMap;
	
	public FindNodeType (){
		nodeTypeMap = new HashMap<String, Calculation>();
		addAllTypes();
	}
	
	public void addAllTypes(){
		//this will add alll basic node function types. 
		//specalty noce types will have to be accessed seperatly and
		//added in after the main node types have been added in. 
		
		{
			//**************************CONSTANTS*********************************
			nodeTypeMap.put("const0", (ArrayList<NetworkCalculationNode> nodes) -> 0);
			nodeTypeMap.put("const1", (ArrayList<NetworkCalculationNode> nodes) -> 1);
			nodeTypeMap.put("const2", (ArrayList<NetworkCalculationNode> nodes) -> 2);
			nodeTypeMap.put("const3", (ArrayList<NetworkCalculationNode> nodes) -> 3);
			nodeTypeMap.put("const4", (ArrayList<NetworkCalculationNode> nodes) -> 4);
			nodeTypeMap.put("const5", (ArrayList<NetworkCalculationNode> nodes) -> 5);
			nodeTypeMap.put("const6", (ArrayList<NetworkCalculationNode> nodes) -> 6);
			nodeTypeMap.put("const7", (ArrayList<NetworkCalculationNode> nodes) -> 7);
			nodeTypeMap.put("const8", (ArrayList<NetworkCalculationNode> nodes) -> 8);
			nodeTypeMap.put("const9", (ArrayList<NetworkCalculationNode> nodes) -> 9);
			nodeTypeMap.put("const10", (ArrayList<NetworkCalculationNode> nodes) -> 10);
			nodeTypeMap.put("constpi", (ArrayList<NetworkCalculationNode> nodes) -> Math.PI);
			nodeTypeMap.put("conste", (ArrayList<NetworkCalculationNode> nodes) -> Math.E);
		}
		
		
		{
			//*************************COMPARITORS********************************
			nodeTypeMap.put("equalto", (ArrayList<NetworkCalculationNode> nodes) -> {
										if (nodes.get(0).getValue() == nodes.get(1).getValue()) {
											return 1;
										} else {
											return 0;
										}});
			nodeTypeMap.put("equaltorange", (ArrayList<NetworkCalculationNode> nodes) -> {
										if (nodes.get(0).getValue() <= nodes.get(1).getValue() + nodes.get(2).getValue()
												&& nodes.get(0).getValue() >= nodes.get(1).getValue() - nodes.get(2).getValue()) {
											return 1;
										} else {
											return 0;
										}
										});
			nodeTypeMap.put("notequalto",(ArrayList<NetworkCalculationNode> nodes) -> {
										if (nodes.get(0).getValue() != nodes.get(1).getValue()) {
											return 1;
										} else {
											return 0;
										}
										});
			nodeTypeMap.put("notequaltorange",(ArrayList<NetworkCalculationNode> nodes) -> {
										// a > b+n or a < b-n
										if (nodes.get(0).getValue() > nodes.get(1).getValue() + nodes.get(2).getValue()
												|| nodes.get(0).getValue() < nodes.get(1).getValue() - nodes.get(2).getValue()) {
											return 1;
										} else {
											return 0;
										}
										});
			nodeTypeMap.put("greaterthan",(ArrayList<NetworkCalculationNode> nodes) -> {
										if (nodes.get(0).getValue() > nodes.get(1).getValue()) {
											return 1;
										} else {
											return 0;
										}
										});
			nodeTypeMap.put("lessthan",(ArrayList<NetworkCalculationNode> nodes) -> {
										if (nodes.get(0).getValue() < nodes.get(1).getValue()) {
											return 1;
										} else {
											return 0;
										}
										});
			nodeTypeMap.put("greaterthanorequalto",(ArrayList<NetworkCalculationNode> nodes) -> {
										if (nodes.get(0).getValue() >= nodes.get(1).getValue()) {
											return 1;
										} else {
											return 0;
										}
										});
			nodeTypeMap.put("lessthanorequalto",(ArrayList<NetworkCalculationNode> nodes) -> {
										if (nodes.get(0).getValue() <= nodes.get(1).getValue()) {
											return 1;
										} else {
											return 0;
										}
										});
		}
		
		
		{//BASIC MATHS
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
		}
		
		
		{//ADVANCED MATHS
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
		}
		
		
		{//SET OPPERATORS
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
		}
		
		
		{//ADJUSTORS
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
		}
		
		
		{//TRIGONOMETRIC FUNCTIONS
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
		}
		
		
		{//BOOLEAN OPERATORS
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
			nodeTypeMap.put("",();
		}
		
		
		{//PASSTHROUGH
			nodeTypeMap.put("",();
		}
		
		
		/*
		switch (command) {
		
		//BASIC MATHS
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
			
		//ADVANCED MATHS
		case "power":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.pow(nodes.get(0).getValue(), nodes.get(1).getValue());
			});
		case "log":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.log(nodes.get(1).getValue())/Math.log(nodes.get(0).getValue());
			});
		case "modulous":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return nodes.get(0).getValue() % nodes.get(1).getValue();
			});
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
					

		//SET OPPERATORS
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
			
		//ADJUSTORS
		case "roundup":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> ((int) nodes.get(0).getValue() + 1));
		case "rounddown":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> ((int) nodes.get(0).getValue()));
		case "roundnearest":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> Math.round(nodes.get(0).getValue()));
		case "increment":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue() + 1);
		case "decrement":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue() - 1);
		case "absolutevalue":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return Math.abs(nodes.get(0).getValue());
			});
		
		
		//TRIGONOMETRY OPERATORS
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
		case "tansig":
			return new NetworkCalculationNode(orderID, command, (ArrayList<NetworkCalculationNode> nodes) -> {
				return (2 / (1 + Math.exp(-2 * nodes.get(0).getValue()))) - 1;
			});
					
		
		//BOOLEAN OPERATORS
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


		//PASSTHROUGH
		case "passthrough":
			return new NetworkCalculationNode(orderID, command,
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue());
		

		/*
		 * case "register": *************NOT IMPLEMENTABLE IN CURRENT
		 * FORMAT******************************* Calculation t =
		 * (ArrayList<NetworkCalculationNode> nodes, memoryValue) -> {
		 * if(nodes.get(1).getValue() == 0){ memoryValue =
		 * nodes.get(0).getValue(); } return memoryValue; }; return new
		 * NetworkCalculationNode(orderID, command,t);
		 /
		default:
			// error log.
			return new NetworkCalculationNode(orderID, "passthrough",
					(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue());
		}
	
		**/
		
		
	}
	
	
	public NetworkCalculationNode findNodeType(String command, int orderID, Object o) {
		//TODO CONVERT TO MAP
		
		
		return (nodeTypeMap.containsKey(command)? 
				(new NetworkCalculationNode(orderID, command, nodeTypeMap.get(command))):
				(new NetworkCalculationNode(orderID, "ErrorPassthrough", 
											(ArrayList<NetworkCalculationNode> nodes) -> nodes.get(0).getValue())));
	}
}
