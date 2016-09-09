package tech.deef.neuralnetwork.network.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class testObject{
	double power;
	double multiplier;
	double adder;
	double resultant;
	
	public testObject(double power, double multiplier, double adder, double resultant){
		this.power = power;
		this.adder = adder;
		this.multiplier = multiplier;
		this.resultant = resultant;
		
		
	}
	
	public double getResultant(){
		return resultant;
	}
	
	public String toString(){
		NumberFormat formatter = new DecimalFormat("#0.0000000"); 
		return ("eqn: Math.pow(base," 
				+ formatter.format(power) + ")*" 
				+ formatter.format(multiplier) + "+" 
				+ formatter.format(adder) 
				+ "\t\t\tresultant: " + formatter.format(resultant));
	}
	
}