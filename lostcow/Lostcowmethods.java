import java.io.*;
import java.util.*;
public class Lostcowmethods {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner stdin = new Scanner(new FileReader("lostcow.in"));
		PrintWriter pw = new PrintWriter(new File("lostcow.out"));
		int x = stdin.nextInt();
		int y = stdin.nextInt();
		pw.print(distance(x, y));
		stdin.close();
		pw.close();
	}
	
	public static int distance(int x, int y) {
		int cnt = 0;
		int newd = x;
		int distance = 0;
		int newdold = x;
		while(contains(x, y, newd)) {
			newd = (x + (int)Math.pow(-1,  cnt) * (int)Math.pow(2,  cnt));
			distance = distance + Math.abs(newd - newdold);
			newdold = newd;
			cnt++;
		}
		return (distance - Math.abs(newd - y));
	}

	public static boolean contains(int x, int y, int newd) {
		return !((x >= y && newd <= y) || (x <= y && newd >= y));
	}
}
