
public class DumbTDH {
	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		long tn = 286;
		long num = 1;
		boolean t = true;
		while(t) {
			
			if((tn * (tn+1))/2 == ((long)(tn * (tn+1))/2)) {
				num = (tn * (tn+1))/2;
				
				
				for(long pn = tn; pn > 0; pn-- ) {
					
					if((pn*(3*pn-1)/2) == num) {
						
						for(long hn = pn; hn > 0; hn--) {
							
							if(hn*(2*hn-1) == num) {
								System.out.println(num);
								t = false;
							}
						}
					}
				}
			}
			tn++;
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));
	}
}	
