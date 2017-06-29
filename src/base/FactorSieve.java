package base;

import java.io.PrintStream;

/**
 * An algorithm which calculates the number of factors in each number up to n.
 * Time complexity: c*(n^2 + n)
 */
public class FactorSieve {
	private final int N;
	private int maxFactor;
	private int maxFactorIndex;
	private int numPrimes;
	private int[] primeFactors;
	private int[] primes;

	public FactorSieve(int n) {
		N = n + 1;
		run();
	}

	/**
	 * Returns an array of integers where A[i] is one less than the number of
	 * factors of i.
	 */
	private void run() {
		int[] sieve = new int[N];
		sieve[1] = 1;
		for (int i = 2; i < N; i++) {
			int k = 2 * i;
			while (k < N) {
				sieve[k]++;
				k += i;
			}
		}
		primeFactors = sieve;
		maxFactor = 0;
		numPrimes = 0;
		primes = new int[N];
		int primeIndex = 0;
		for (int i = 2; i < N; i++) {
			if (primeFactors[i] > maxFactor) {
				maxFactor = primeFactors[i];
				maxFactorIndex = i;
			}
			if (primeFactors[i] == 0){
				numPrimes++;
				primes[primeIndex++] = i;
			}
			
		}
	}

	public void print(PrintStream s) {
		for (int i = 0; i < primeFactors.length; i++) {
			s.println(i + ": " + (primeFactors[i] + 1));
		}
	}

	public int getMaxFactor() {
		return maxFactor;
	}

	public int getMaxFIndex() {
		return maxFactorIndex;
	}

	public int[] getFactors() {
		return primeFactors;
	}
	
	public int getN() {
		return N;
	}
		
	public int getPrimeAmt() {
		return numPrimes;
	}
	
	public int[] getPrimes() {
		return primes;
	}
}
