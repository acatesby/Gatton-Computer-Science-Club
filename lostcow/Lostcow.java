import java.io.*;
import java.util.*;
public class Lostcow {
	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(new FileReader("lostcow.in"));
		StringTokenizer st = new StringTokenizer(stdin.readLine());
		PrintWriter pw = new PrintWriter(new File("lostcow.out"));
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int cnt = 0;
		int newd = x;
		int distance = 0;
		int newdold = x;
		while(!((x >= y && newd <= y) || (x <= y && newd >= y))) {
			newd = (x + (int)Math.pow(-1,  cnt) * (int)Math.pow(2,  cnt));
			distance = distance + Math.abs(newd - newdold);
			newdold = newd;
			cnt++;
		}
		pw.print(distance - Math.abs(newd - y));
		stdin.close();
		pw.close();
	}
}
