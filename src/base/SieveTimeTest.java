package base;

public class SieveTimeTest {

	public static void main(String[] args) {
		int n = 1;
		double[] C = new double[n];
		for (int i = 0; i < 0; i++){
			long sTime = System.currentTimeMillis();
			double rN = Math.random()+ 0.2;
			int tN = (int)(rN* 10000000);
			FactorSieve S = new FactorSieve(tN);
			long fTime = System.currentTimeMillis() - sTime;
			double exp = (rN * rN + rN);
			C[i] = fTime/exp;
			System.out.println("************\nn = " + tN + ", " + fTime + " ms, c = " + C[i] + "\nrN: " + rN);
		}
		int[] P = new FactorSieve(1000).getFactors();
		for (int i = 0; i < 1000; i++)
			if (P[i] == 0)
				System.out.println(i + " is prime.");
	}

}
