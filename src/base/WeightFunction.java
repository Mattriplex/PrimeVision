package base;

public class WeightFunction {
	private WeightFunction f;

	public WeightFunction(int type) {
		switch (type) {
		case 0:
			break;
		case 1:
			f = new LinearF(0);
			break;
		case 2:
			f = new LogF(0);
			break;
		case 3:
			f = new RootF(0);
		default:
			f = new LinearF(0);
		}
	}
	
	public double get(int fac, int max){
		return f.get(fac, max);
	}

	private class LinearF extends WeightFunction{
		public LinearF(int type) {
			super(0);
		}

		public double get(int fac, int max) {
			return (double) fac / (double) max;
		}
	}

	private class LogF extends WeightFunction{
		public LogF(int type) {
			super(0);
		}

		public double get(int fac, int max) {
			return Math.log(fac)/Math.log(max);
		}
	}
	private class RootF extends WeightFunction{
		public RootF(int type) {
			super(0);
		}

		public double get(int fac, int max) {
			return Math.sqrt(fac)/Math.sqrt(max);
		}
	}

}
