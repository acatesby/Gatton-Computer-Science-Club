
/*
ID: acatesby
LANG: JAVA
PROB: moobuzz
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class moobuzz {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("moobuzz.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		//target: the target
		int target = Integer.parseInt(st.nextToken());
		//current: the current-th number
		int current = 0;
		//the minimum value of a range of 30
		int min = 0;
		
		//narrow down the range of possible numbers
		while(current < target) {
			current += 16;
			min += 30;
		}
		
		//floor min and current, use num to store current number
		min -= 30;
		current -= 16;
		int num = 0;
		
		
		//iterate through the range of 30 and find the number
		for(int i = min; i <= min + 30 && current < target; i++) {
			if(i % 3 != 0 && i % 5 != 0) {
				current++;
			}
			num = i;
		}
		
		out.print(num);
		out.close();
		
	}
}
