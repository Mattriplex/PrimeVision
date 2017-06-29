package factorwiz;

import java.util.ArrayList;
import java.util.HashMap;

public class FactWiz {

	public static void main(String[] args) {
		Factorifier C = new Factorifier(100);
		C.calculate();
		ArrayList<HashMap<Integer, Integer>> factors = C.getFactorList();
		for (int i = 2; i < 100; i++){
			System.out.println(i + ":" + factors.get(i).toString());
		}
	}

}
