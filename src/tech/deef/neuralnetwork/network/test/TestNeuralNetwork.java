package tech.deef.neuralnetwork.network.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import tech.deef.neuralnetwork.network.calculate.NeuralNetworkCalculation;
import tech.deef.neuralnetwork.network.nodes.NetworkCalculationNode;

//designed to test and create tsting methods for the neural network
public class TestNeuralNetwork {

	public static void main(String args[]) {
		basicNetworkTest1();
	}

	//print the output of 1/x from x=1-1000
	private static void basicNetworkTest1() {
		int expectedOutputNodeID = 0;
		
		try {

			String input = "";
			ArrayList<NetworkCalculationNode> network;
				// create storaeg network
			File testFile1 = new File("testfile1.txt");
				// get the test file piece 1
			Scanner scanner = new Scanner(testFile1);
				// build the scanner from the file location
				
			while(scanner.hasNext()){
				input += scanner.next();
			}
			input = input.replaceAll("\\s+", "");
			
			NeuralNetworkCalculation testNetwork1 = new NeuralNetworkCalculation(input, expectedOutputNodeID);
			// create the network calculation object using the input string. 

			testNetwork1.calculateNetwork(100);
			
			double result = testNetwork1.getResultantValue();
			int cycles = testNetwork1.getItterations();
			System.out.printf("%.2f in %d cycle\n", result, cycles);
			
			
			System.out.println(testNetwork1.toString());
			
			
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	// designed to test the basic functions of the neural network with hard coded nodes as input
	//along with a hand writtern network.
	public static void basicNetworkTest2() {
		int expectedOutputNodeID = 0;

		try {

			String input = "";
			ArrayList<NetworkCalculationNode> network;
				// create storaeg network
			File testFile1 = new File("testfile2.txt");
				// get the test file piece 1
			Scanner scanner = new Scanner(testFile1);
				// build the scanner from the file location
				
			while(scanner.hasNext()){
				input += scanner.next();
			}
			input = input.replaceAll("\\s+", "");//remove excess whitespace. 
			ArrayList<NetworkCalculationNode> preparedNodes = null;
			//create the prepared nodes. 
				// build the network using the prepared nodes. 
				// get te location of the output node.
		

			NeuralNetworkCalculation testNetwork1 = new NeuralNetworkCalculation(input, preparedNodes, expectedOutputNodeID);
				// create the network calculation object

			testNetwork1.calculateNetwork(100);
				// test the network with an infinate number of itterations.

			double result = testNetwork1.getResultantValue();
			int cycles = testNetwork1.getItterations();
			System.out.printf("%.2f in %d cycles", result, cycles); //result number of cycles is not functioning
				// print final values.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
