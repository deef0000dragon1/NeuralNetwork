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
		// double adder = .17440;
		// double power = .2803010;
		for (double power = .280; power <= .285; power += .00001) {
			for (double multiplier = .710; multiplier <= .711; multiplier += .0000001) {
				for (double adder = .172; adder <= .178; adder += .000001) {
					r = calcResultant(power, multiplier, adder);
					if (r <= large) {
						testobj = new testObject(power, multiplier, adder, r);
						tested.add(testobj);
						large = r;
						if (System.currentTimeMillis() > time + 500) {
							time = System.currentTimeMillis();
							System.out.println(testobj.toString() + " Size: " + tested.size());
						}
					}
				}
			}
		}

		Collections.sort(tested, new Comparator<testObject>() {

			public int compare(testObject o1, testObject o2) {
				return Double.compare(o2.getResultant(), o1.getResultant());
			}
		});

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
