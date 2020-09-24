
public class TDH {
	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		long n = 144;
		long h = 1;
		
		boolean t = true;
		while(t) {
			h = n*(2*n-1);
			
			if((1+Math.sqrt(1+24*h))/6 == (int)(1+Math.sqrt(1+24*h))/6) {
				System.out.println(h);
				t = false;
			} else
				n++;
			
			
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));
	}
}
