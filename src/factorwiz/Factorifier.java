package factorwiz;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Factorifier {
	private final int N;
	private ArrayList<HashMap<Integer, Integer>> factors;
	private ArrayList<ArrayList<Integer>> primes;

	public Factorifier(int n) {
		primes = new ArrayList<ArrayList<Integer>>(); // 2d array holding all
														// powers of primes
		N = n + 1;
		factors = new ArrayList<HashMap<Integer, Integer>>();
		for (int i = 0; i < N; i++) {
			factors.add(i, new HashMap<Integer, Integer>());
		}
	}

	public void calculate() {
		int primeI = 0;
		for (int i = 2; i < N; i++) {
			// if i is prime
			if (factors.get(i).isEmpty()) {
				primes.add(new ArrayList<Integer>());
				for (int j = i; j < N; j *= i) {
					primes.get(primeI).add(j);
				}
				primeI++;
				int tempSum = i;
				while (tempSum < N) {
					if (factors.get(tempSum).get(i) == null) {
						factors.get(tempSum).put(i, 1);
					} else
						factors.get(tempSum).put(i,
								factors.get(tempSum).get(i) + 1);
					tempSum += i;
				}
			}

		}
	}

	public void calculate2() {
		int primeI = 0;
		for (int i = 2; i < N; i++) {
			// if i is prime
			if (factors.get(i).isEmpty()) {
				primes.add(new ArrayList<Integer>());
				primes.get(primeI).add(1);
				for (int j = i; j < N; j *= i) {
					primes.get(primeI).add(j);
				}
				int tempS = 0;
				for (int j = 0; j < primeI; j++) {
					int powi = 1;
					for (int kj = 0; kj < primes.get(j).size(); kj++) {
						tempS = i * primes.get(j).get(kj);
						while (tempS < N) {
							factors.get(tempS).put(j, kj + 1);
							factors.get(tempS).put(i, powi);
						}
					}
				}
				primeI++;
				int tempSum = i;

				while (tempSum < N) {
					if (factors.get(tempSum).get(i) == null) {
						factors.get(tempSum).put(i, 1);
					} else
						factors.get(tempSum).put(i,
								factors.get(tempSum).get(i) + 1);
					tempSum += i;
				}
			}

		}
	}
	
	private void calcProducts() {
		int currentpos = 0;
		
	}

	public ArrayList<HashMap<Integer, Integer>> getFactorList() {
		return factors;
	}

	public ArrayList getPrimeList() {
		return primes;
	}

}
