/*
ID: acatesby
LANG: JAVA
PROB: lifeguards
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class lifeguards {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		//allocate shifts array
		int[][] times = new int[Integer.parseInt(st.nextToken())][2];
		for(int i = 1; i <= times.length; i++) {
			st = new StringTokenizer(f.readLine());
			times[i][0] = Integer.parseInt(st.nextToken());
			times[i][1] = Integer.parseInt(st.nextToken());
		}

		//fire every possible cow and compute total time when they gone
		int max = 0;
		for(int i = 0; i < times.length; i++) {
			int[][] temp = remove(times, i);
			
			int hrs = totalTime(temp);
			if(hrs > max)
				max = hrs;
		}
		
		f.close();
		out.println(max);
		out.close();
	}
	
	//a method to get the total time the pool is guarded
	public static int totalTime(int[][] shifts) {
		int total = 0;
		int[] timeline = new int[1000];
		
		for(int i = 0; i < shifts.length; i++) {
			for(int j = shifts[i][0]; j < shifts[i][1]; j++) {
				timeline[j] = 1;
			}
		}
		
		for(int i = 0; i < timeline.length; i++) {
			if(timeline[i] == 1)
				total++;
		}
		
		return total;
	}
	
	//a method to remove an element of an array
	public static int[][] remove(int[][] array, int index) {
		int[][] ret = new int[array.length - 1][2];
		
		int dis = 0;
		for(int i = 0; i < array.length; i++) {
			if(i == index) {
				dis++;
				continue;
			} else {
				ret[i - dis] = array[i];
			}
		}
		return ret;
	}
	

}
