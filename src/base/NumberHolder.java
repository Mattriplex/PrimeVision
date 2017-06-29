package base;

public class NumberHolder implements Comparable<NumberHolder> {
	public final int x;
	public final int y;
	public final int size;
	public final int color;
	public NumberHolder(int x, int y, int size, int color){
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}
	
	public int compareTo(NumberHolder arg0) {
		if (this.size > arg0.size)
			return 1;
		if (this.size < arg0.size)
			return -1;
		return 0;
	}
	
}
