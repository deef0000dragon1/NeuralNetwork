package tech.deef.neuralnetwork.network.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DetermineOptimalSettingsForMutatorFunction {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		ArrayList<testObject> tested = new ArrayList<testObject>();
		testObject testobj = null;
		double r = 0;
		double large = 1;
		double accuracy = .01;
		double plo = .1;
		double phi = 1;
		double mlo = .01;
		double mhi = 1;
		double alo = .01;
		double ahi = 1;
		
		while (accuracy > .0000000000001) {
			tested.clear();
			for (double power = plo; power <= phi; power += accuracy) {
				for (double multiplier = mlo; multiplier <= mhi; multiplier += accuracy) {
					for (double adder = alo; adder <= ahi; adder += accuracy) {
						r = calcResultant(power, multiplier, adder);
						if (r <= large) {
							testobj = new testObject(power, multiplier, adder, r);
							tested.add(testobj);
							large = r;
						}
					}
				}
			}
			
			Collections.sort(tested, new Comparator<testObject>() {

				public int compare(testObject o1, testObject o2) {
					return Double.compare(o1.getResultant(), o2.getResultant());
				}
			});
			
			plo = tested.get(0).getPower()-accuracy*10;
			phi = tested.get(0).getPower()+accuracy*10;
			mlo = tested.get(0).getMultiplier()-accuracy*10;
			mhi = tested.get(0).getMultiplier()+accuracy*10;
			alo = tested.get(0).getAdder()-accuracy*10;
			ahi = tested.get(0).getAdder()+accuracy*10;
			System.out.println(tested.get(0).toString());
			accuracy = accuracy /10.0;
			System.out.println("Accuracy: " + accuracy*10);
			
		}


		System.out.println("low " + tested.get(0).toString());
		System.out.println(tested.size());

	}

	private static double[][] inputs = { { .001, .01, .05, .1, .2, .33, .45, .75, 1 },
			{ .275, .365, .5, .56, .625, .6873, .735, .83, .885 } };

	private static double calcResultant(double power, double multiplier, double adder) {
		double val = 0;
		for (int i = 0; i < inputs[0].length; i++) {

			val += Math.abs(inputs[1][i] - (Math.pow(inputs[0][i], power) * multiplier + adder));
		}
		return val;
	}

}
