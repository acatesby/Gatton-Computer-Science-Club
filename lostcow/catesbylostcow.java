/*
ID: acatesby
LANG: JAVA
PROB: lostcow
*/
import java.io.*;
import java.util.*;

public class catesbylostcow {
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("lostcow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lostcow.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int x = Integer.parseInt(st.nextToken());;
		int y = Integer.parseInt(st.nextToken());;
		int pos = 1;
		int total = 0;
		int cur = 0;
		for(int i = 1; i <= Math.pow(Math.abs(x-y), 9); i++){
			if(i != 1)
				pos = pos*2;
			while(x + cur != y && Math.abs(cur) < pos) {
				if(i % 2 == 1)
					cur++;
				else
					cur--;
				total++;
			}
			if(x + cur == y)
				break;
		}
		out.println(total);
		out.close();
	}
}
